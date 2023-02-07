package com.he1extg.converterui.service

import com.he1extg.converterui.dto.FileUploadDTO
import com.he1extg.converterui.dto.FileConvertDTO
import com.he1extg.converterui.dto.FilenameBytearrayDTO
import com.he1extg.converterui.dto.IdFilenameDTO
import com.he1extg.converterui.feign.ApiClient
import com.he1extg.converterui.feign.DataClient
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ConverterServiceImpl(
    val apiClient: ApiClient,
    val dataClient: DataClient
) : ConverterService {

    /**
     * TODO Get user from security authentication
     */
    val user: String
        get() = SecurityContextHolder.getContext().authentication.name

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
           val newFilename = (converterFile().fileName.substringBeforeLast('.')) + ".mp3"
           FileUploadDTO(convertResult, newFilename, user)
        }
    }

    override fun downloadFile(id: Long): FilenameBytearrayDTO {
        return dataClient.downloadFile(id)
    }

}