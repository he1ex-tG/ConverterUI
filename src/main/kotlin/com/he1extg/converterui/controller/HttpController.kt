package com.he1extg.converterui.controller

import com.he1extg.converterui.service.ConverterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile

@Controller
class HttpController {

    @Autowired
    lateinit var converterService: ConverterService

    @GetMapping
    fun main(): String {
        /** TEST */
        converterService.getFileList()
        return "index"
    }

    /**
     * TEST
     */
    @GetMapping("/test")
    @ResponseBody
    fun test(): String {
        return SecurityContextHolder.getContext().authentication.name
    }

    /**
     * TEST
     */
    @PostMapping
    fun uploadFile(@RequestParam file: MultipartFile): String {
        if (!file.isEmpty) {

        }
        return "redirect:/"
    }
}