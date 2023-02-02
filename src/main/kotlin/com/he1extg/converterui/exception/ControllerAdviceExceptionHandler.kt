package com.he1extg.converterui.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@ControllerAdvice
class ControllerAdviceExceptionHandler {

    /**
     * Default exception handlers
     */

    /**
     * Custom exception handlers
     */
    @ExceptionHandler(ApiClientException::class)
    fun handlerApiClientException(ex: ApiClientException, redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("apiError", "ConverterAPI exception: ${ex.apiError.message}")
        return "redirect:/"
    }

    @ExceptionHandler(DataClientException::class)
    fun handlerDataClientException(ex: DataClientException, redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("apiError", "ConverterData exception: ${ex.apiError.message}")
        return "redirect:/"
    }
}