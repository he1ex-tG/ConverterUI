package com.he1extg.converterui.service

import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.model.TransferData
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
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

    private fun convertFile(transferData: TransferData): ByteArray? {
        val requestEntity = RequestEntity.post(converterServiceConfiguration.uriApi)
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                transferData
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

    private fun storeFile(userName: String, fileName: String, file: ByteArray): Boolean {
        val requestEntity = RequestEntity.get(converterServiceConfiguration.uriData).build()
        val restTemplate = RestTemplate()
        val answer = restTemplate.exchange(requestEntity, ResponseEntity::class.java)
        return answer.statusCode.is2xxSuccessful
    }

    override fun processFile(converterFile: ConverterFile): Boolean {
        val convertTransferData = TransferData().apply {
            content = converterFile.file?.bytes
        }
        val convertedByteArray = convertFile(convertTransferData)
        return convertedByteArray != null
    }

}