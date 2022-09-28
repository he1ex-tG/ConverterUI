package com.he1extg.converterui.service

import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ConverterServiceImpl(
    private val converterServiceConfiguration: ConverterServiceConfiguration,
): ConverterService {

    @Suppress("UNCHECKED_CAST")
    override fun getFileList(): List<String> {
        val requestEntity = RequestEntity.get(converterServiceConfiguration.uriData).build()
        val restTemplate = RestTemplate()
        val answer = restTemplate.exchange(requestEntity, List::class.java)
        println("Answer: ${answer.body}")
        return answer.body as List<String>
    }
}