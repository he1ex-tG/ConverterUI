package com.he1extg.converterui.exception

import com.he1extg.converterui.dto.IdFilenameDTO
import com.he1extg.converterui.exception.client.ClientException
import com.he1extg.converterui.exception.client.ApiClientException
import com.he1extg.converterui.exception.client.DataClientException
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

    @ExceptionHandler(ApiClientException::class)
    fun handlerApiClientException(ex: ClientException, redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("apiError", ex)
        return "redirect:/"
    }

    @ExceptionHandler(DataClientException::class)
    fun handlerDataClientException(ex: ClientException, redirectAttributes: RedirectAttributes): String {
        redirectAttributes.addFlashAttribute("apiError", ex)
        redirectAttributes.addFlashAttribute("storedFiles", emptyList<IdFilenameDTO>())
        return "redirect:/"
    }
}