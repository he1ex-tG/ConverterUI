package com.he1extg.converterui.exception.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [MultipartFileValidator::class])
@Target(AnnotationTarget.FIELD)
annotation class MultipartFileConstraint(
    val message: String = "Invalid MultipartFile.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)