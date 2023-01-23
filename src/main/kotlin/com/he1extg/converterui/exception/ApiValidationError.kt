package com.he1extg.converterui.exception

class ApiValidationError(
    val `object`: String,
    val message: String,
    val field: String,
    val rejectedValue: Any
)