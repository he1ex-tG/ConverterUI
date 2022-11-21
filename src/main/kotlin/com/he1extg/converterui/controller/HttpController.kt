package com.he1extg.converterui.controller

import com.he1extg.converterui.service.ConverterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HttpController {

    @Autowired
    lateinit var converterService: ConverterService

    @GetMapping
    fun main(model: Model): String {
        return "index"
    }
}