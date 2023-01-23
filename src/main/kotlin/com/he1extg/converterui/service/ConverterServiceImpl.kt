package com.he1extg.converterui.service

import com.he1extg.converterui.dto.FileUploadDTO
import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.dto.FileConvertDTO
import com.he1extg.converterui.dto.FilenameBytearrayDTO
import com.he1extg.converterui.dto.IdFilenameDTO
import com.he1extg.converterui.feign.ApiClient
import com.he1extg.converterui.feign.DataClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ConverterServiceImpl : ConverterService {

    @Autowired
    lateinit var apiClient: ApiClient
    @Autowired
    lateinit var dataClient: DataClient

    /**
     * TODO Get user from security authentication
     */
    val user: String = "SuperUser"

    override fun getFileList(): List<IdFilenameDTO> {
        return dataClient.getFileList(user)
    }

    private fun convertFile(fileContent: ByteArray): ByteArray {
        val fileConvertDTO = FileConvertDTO(fileContent)
        val answerApi = apiClient.convertFile(fileConvertDTO)
        return answerApi.content
    }

    private fun storeFile(userName: String, fileName: String, fileContent: ByteArray) {
        val fileUploadDTO = FileUploadDTO(fileContent, fileName, userName)
        dataClient.uploadFile(fileUploadDTO)
    }

    override fun processFile(converterFile: ConverterFile) {
        val multipartFile = converterFile.file ?: return // add validation on controller layer ?: return false
        val convertResult = convertFile(multipartFile.bytes)

        /** Change file extension from PDF to MP3 */
        val newFilename = (multipartFile.originalFilename?.substringBeforeLast('.') ?: "filename") + ".mp3"

        val storeResult = storeFile(user, newFilename, convertResult)
    }

    override fun downloadFile(id: Long): FilenameBytearrayDTO {
        return dataClient.downloadFile(id)
    }

}