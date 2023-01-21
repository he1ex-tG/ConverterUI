package com.he1extg.converterui.feign

import com.he1extg.converterui.dto.FileConvertDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "DataClient",
    url = "\${feign.client.data.uri}",
    configuration = [DataClientConfiguration::class]
)
interface DataClient {


}