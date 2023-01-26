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
import org.springframework.web.multipart.MultipartFile

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

    private fun convertFile(fileConvertDTO: () -> FileConvertDTO): ByteArray {
        return apiClient.convertFile(fileConvertDTO()).content
    }

    private fun storeFile(fileUploadDTO: () -> FileUploadDTO) {
        dataClient.uploadFile(fileUploadDTO())
    }

    override fun processFile(converterFile: () -> FilenameBytearrayDTO) {
        val convertResult = convertFile {
            FileConvertDTO(converterFile().file)
        }
        val storeResult = storeFile {
           /** Change file extension from PDF to MP3 */
           val newFilename = (converterFile().fileName.substringBeforeLast('.') ?: "filename") + ".mp3"
           FileUploadDTO(convertResult, newFilename, user)
        }
    }

    override fun downloadFile(id: Long): FilenameBytearrayDTO {
        return dataClient.downloadFile(id)
    }

}