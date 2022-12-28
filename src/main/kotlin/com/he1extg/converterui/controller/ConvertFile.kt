package com.he1extg.converterui.controller

import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.service.ConverterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

@Controller
@SessionAttributes("converterFile")
class ConvertFile {

    @Autowired
    lateinit var converterService: ConverterService

    @ModelAttribute(name = "converterFile")
    fun converterFile(): ConverterFile {
        return ConverterFile()
    }

    @GetMapping
    fun main(): String {
        return "index"
    }

    @PostMapping
    fun upload(
        @ModelAttribute converterFile: ConverterFile,
        sessionStatus: SessionStatus
    ): String {
        converterService.processFile(converterFile)
        sessionStatus.setComplete()
        return "index"
    }
}