package com.he1extg.converterui.service

import com.he1extg.converterui.dto.FileUploadDTO
import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.dto.FileConvertDTO
import com.he1extg.converterui.feign.ApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ConverterServiceImpl(
    private val converterServiceConfiguration: ConverterServiceConfiguration,
): ConverterService {

    @Autowired
    lateinit var apiClient: ApiClient

    /**
     * TODO Get user from security authentication
     */
    val user: String = "SuperUser"

    val restTemplate: RestTemplate = RestTemplate().apply {
        //errorHandler = MyResponseErrorHandler()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getFileList(): List<String> {
        val requestEntity = RequestEntity.get(converterServiceConfiguration.uriData).build()
        val restTemplate = RestTemplate()
        val answer = restTemplate.exchange(requestEntity, List::class.java)
        println("Answer: ${answer.body}")
        return answer.body as List<String>
    }

    private fun convertFile(fileContent: ByteArray): ByteArray {
        val fileConvertDTO = FileConvertDTO(fileContent)
        val answer = apiClient.convertFile(fileConvertDTO)
        return answer.content
    }

    private fun storeFile(userName: String, fileName: String, fileContent: ByteArray): Boolean {
        val fileUploadDTO = FileUploadDTO(fileContent, fileName, userName)
        val requestEntity = RequestEntity.post(converterServiceConfiguration.uriData)
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                fileUploadDTO
            )
        return try {
            val answer = restTemplate.exchange(requestEntity, Unit::class.java)
            true
        }
        catch (e: Exception) {
            false
        }
    }

    override fun processFile(converterFile: ConverterFile): Boolean {
        val multipartFile = converterFile.file ?: return false

        //try {
            val convertResult = convertFile(multipartFile.bytes) ?: return false
        /*} catch (e: Exception) {
            println(e.message)
        }*/

        /*val storeResult = storeFile(
            user,
            multipartFile.name,
            convertResult
        )

        return storeResult*/
        return true
    }

}