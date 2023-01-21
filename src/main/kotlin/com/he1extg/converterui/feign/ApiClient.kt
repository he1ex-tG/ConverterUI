package com.he1extg.converterui.feign

import com.he1extg.converterui.dto.FileConvertDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "ApiClient",
    url = "\${feign.client.api.uri}",
    configuration = [ApiClientConfiguration::class]
)
interface ApiClient {

    @PostMapping("/")
    fun convertFile(fileConvertDTO: FileConvertDTO): FileConvertDTO
}