package com.he1extg.converterui.service

import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.model.TransferData
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile

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

    @Suppress("UNCHECKED_CAST")
    private fun convertFile(transferData: TransferData): ByteArray? {
        val requestEntity = RequestEntity.post(converterServiceConfiguration.uriApi)
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                transferData
            )
        val restTemplate = RestTemplate()
        val answer = restTemplate.exchange(requestEntity, ByteArray::class.java)
        println(answer.statusCode)
        println(answer.body)
        return if (answer.statusCode.is2xxSuccessful) {
            answer.body as ByteArray
        } else {
            null
        }
    }

    private fun storeFile(userName: String, fileName: String, file: ByteArray): Boolean {
        val requestEntity = RequestEntity.get(converterServiceConfiguration.uriData).build()
        val restTemplate = RestTemplate()
        val answer = restTemplate.exchange(requestEntity, ResponseEntity::class.java)
        return answer.statusCode.is2xxSuccessful
    }

    override fun processFile(converterFile: ConverterFile): Boolean {
        /*val convertResult = convertFile(file) ?: return false
        return storeFile(user, file.name, convertResult)*/
        return convertFile(converterFile.transferData) != null
    }

}