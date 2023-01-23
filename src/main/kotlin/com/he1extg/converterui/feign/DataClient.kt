package com.he1extg.converterui.feign

import com.he1extg.converterui.dto.FileUploadDTO
import com.he1extg.converterui.dto.FilenameBytearrayDTO
import com.he1extg.converterui.dto.IdFilenameDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "DataClient",
    url = "\${feign.client.data.uri}",
    configuration = [DataClientConfiguration::class]
)
interface DataClient {

    @PostMapping("/")
    fun uploadFile(fileUploadDTO: FileUploadDTO)

    @GetMapping("/")
    fun getFileList(@RequestParam username: String): List<IdFilenameDTO>

    @GetMapping("/{id}")
    fun downloadFile(@PathVariable id: Long): FilenameBytearrayDTO
}