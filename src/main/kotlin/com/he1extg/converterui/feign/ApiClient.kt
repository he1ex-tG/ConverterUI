package com.he1extg.converterui.feign

import com.he1extg.converterui.dto.FileConvertDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "ApiClient",
    url = "http://localhost:8082/api/v1/file",
    configuration = [ApiClientConfiguration::class]
)
interface ApiClient {

    @PostMapping("/")
    fun convertFile(fileConvertDTO: FileConvertDTO): FileConvertDTO
}