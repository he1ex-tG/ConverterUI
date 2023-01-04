package com.he1extg.converterui.service

import com.he1extg.converterui.dto.FileUploadDTO
import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.dto.FileConvertDTO
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ConverterServiceImpl(
    private val converterServiceConfiguration: ConverterServiceConfiguration,
): ConverterService {

    val user: String = "SuperUser"

    @Suppress("UNCHECKED_CAST")
    override fun getFileList(): List<String> {
        val requestEntity = RequestEntity.get(converterServiceConfiguration.uriData).build()
        val restTemplate = RestTemplate()
        val answer = restTemplate.exchange(requestEntity, List::class.java)
        println("Answer: ${answer.body}")
        return answer.body as List<String>
    }

    private fun convertFile(fileContent: ByteArray): ByteArray? {
        val fileConvertDTO = FileConvertDTO(fileContent)
        val requestEntity = RequestEntity.post(converterServiceConfiguration.uriApi)
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                fileConvertDTO
            )
        val restTemplate = RestTemplate()
        return try {
            val answer = restTemplate.exchange(requestEntity, ByteArray::class.java)
            answer.body
        }
        catch (e: Exception) {
            println(e.message)
            null
        }
    }

    private fun storeFile(userName: String, fileName: String, fileContent: ByteArray): Boolean {
        val fileUploadDTO = FileUploadDTO(fileContent, fileName, userName)
        val requestEntity = RequestEntity.post(converterServiceConfiguration.uriData)
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                fileUploadDTO
            )
        val restTemplate = RestTemplate()
        val answer = restTemplate.exchange(requestEntity, Unit::class.java)
        return answer.statusCode == HttpStatus.OK
    }

    override fun processFile(converterFile: ConverterFile): Boolean {
        val multipartFile = converterFile.file ?: return false

        val convertResult = convertFile(multipartFile.bytes) ?: return false
        val storeResult = storeFile(
            user,
            multipartFile.originalFilename ?: "defaultFilename",
            convertResult
        )

        return storeResult
    }

}