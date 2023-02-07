package com.he1extg.converterui.feign

import com.he1extg.converterui.dto.FileUploadDTO
import com.he1extg.converterui.dto.FilenameBytearrayDTO
import com.he1extg.converterui.dto.IdFilenameDTO
import com.he1extg.converterui.dto.UsernamePasswordDTO
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

    @PostMapping("files/")
    fun uploadFile(fileUploadDTO: FileUploadDTO)

    @GetMapping("files/")
    fun getFileList(@RequestParam username: String): List<IdFilenameDTO>

    @GetMapping("files/{id}")
    fun downloadFile(@PathVariable id: Long): FilenameBytearrayDTO

    @GetMapping("users/{username}")
    fun getUser(@PathVariable username: String): UsernamePasswordDTO

    @PostMapping("users/")
    fun addUser(usernamePasswordDTO: UsernamePasswordDTO)
}