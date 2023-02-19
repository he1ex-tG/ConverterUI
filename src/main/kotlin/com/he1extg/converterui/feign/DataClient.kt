package com.he1extg.converterui.feign

import com.he1extg.converterdata.dto.file.ContentDTO
import com.he1extg.converterdata.dto.file.FileUploadDTO
import com.he1extg.converterdata.dto.file.FilenameDTO
import com.he1extg.converterdata.dto.user.AuthenticationDTO
import com.he1extg.converterdata.dto.user.NewUserDTO
import com.he1extg.converterdata.dto.user.UserDTO
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

    /**
     * Files section
     */

    @PostMapping("files/")
    fun uploadFile(fileUploadDTO: FileUploadDTO)

    @GetMapping("files/")
    fun getFileList(@RequestParam userId: Long): List<FilenameDTO>

    @GetMapping("files/{id}")
    fun downloadFile(@PathVariable id: Long): ContentDTO

    /**
     * Users section
     */

    @GetMapping("users/{username}")
    fun getUserAuthentication(@PathVariable username: String): AuthenticationDTO

    @GetMapping("users/{username}")
    fun getUser(@PathVariable username: String): UserDTO

    @PostMapping("users/")
    fun addUser(newUserDTO: NewUserDTO)
}