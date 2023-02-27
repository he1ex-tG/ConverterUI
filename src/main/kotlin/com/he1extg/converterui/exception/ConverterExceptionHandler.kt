package com.he1extg.converterui.exception

import com.he1extg.converterui.exception.client.ClientException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@ControllerAdvice
class ConverterExceptionHandler {

    /**
     * Default exception handlers
     */

    /**
     * Custom exception handlers
     */

    @ExceptionHandler(ClientException::class)
    fun handlerApiClientException(ex: ClientException, redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("apiError", ex)
        return "redirect:/error_template"
    }

    /*@ExceptionHandler(DataClientRetryerException::class)
    fun handlerDataClientException(ex: ClientException, redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("apiError", ex)
        return "redirect:/error_template"
    }*/
}