package com.he1extg.converterui.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HttpController {

    @GetMapping
    fun main(): String {
        return "index"
    }
}