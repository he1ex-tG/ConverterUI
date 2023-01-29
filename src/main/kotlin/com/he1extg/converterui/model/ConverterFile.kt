package com.he1extg.converterui.model

import com.he1extg.converterui.exception.validation.MultipartFileConstraint
import org.springframework.web.multipart.MultipartFile

class ConverterFile {
    @field:MultipartFileConstraint(message = "Invalid file to convert.")
    var file: MultipartFile? = null
}