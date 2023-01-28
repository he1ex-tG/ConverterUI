package com.he1extg.converterui.exception

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdviceExceptionHandler {

    /**
     * Default exception handlers
     */

    /**
     * Custom exception handlers
     */
    @ExceptionHandler(ApiClientException::class)
    fun handlerApiClientException(ex: ApiClientException, model: Model): String {
        model.addAttribute(ex.apiError)
        return "redirect:/"
    }

    @ExceptionHandler(DataClientException::class)
    fun handlerDataClientException(ex: DataClientException, model: Model): String {
        model.addAttribute(ex.apiError)
        return "redirect:/"
    }

}