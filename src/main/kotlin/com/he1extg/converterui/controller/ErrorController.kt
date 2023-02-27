package com.he1extg.converterui.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ErrorController {

    @GetMapping("/error_template")
    fun getErrorPage(): String {
        return "error_template"
    }
}