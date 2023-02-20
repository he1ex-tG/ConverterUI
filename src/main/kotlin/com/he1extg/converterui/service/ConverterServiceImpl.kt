package com.he1extg.converterui.service

import com.he1extg.converterui.dto.file.ContentDTO
import com.he1extg.converterui.dto.file.FileUploadDTO
import com.he1extg.converterui.dto.file.FilenameDTO
import com.he1extg.converterui.dto.file.FileConvertDTO
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

    override fun getFileList(userId: Long): List<FilenameDTO> {
        return dataClient.getFileList(userId)
    }

    private fun convertFile(block: () -> FileConvertDTO): ByteArray {
        return apiClient.convertFile(block()).content
    }

    private fun storeFile(block: () -> FileUploadDTO) {
        dataClient.uploadFile(block())
    }

    override fun processFile(block: () -> FileUploadDTO) {
        val convertResult = convertFile {
            FileConvertDTO(block().content)
        }
        val storeResult = storeFile {
           /** Change file extension from PDF to MP3 */
           val newFilename = (block().filename.substringBeforeLast('.')) + ".mp3"
           FileUploadDTO(convertResult, newFilename, block().userId)
        }
    }

    override fun downloadFile(fileId: Long): ContentDTO {
        return dataClient.downloadFile(fileId)
    }

}