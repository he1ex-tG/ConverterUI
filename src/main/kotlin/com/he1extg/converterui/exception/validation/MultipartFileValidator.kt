package com.he1extg.converterui.exception.validation

import org.springframework.web.multipart.MultipartFile
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class MultipartFileValidator : ConstraintValidator<MultipartFileConstraint, MultipartFile> {

    override fun isValid(value: MultipartFile?, context: ConstraintValidatorContext?): Boolean {
        return (value != null) && (value.size > 0)
    }
}