package com.he1extg.converterui.model

import javax.validation.constraints.NotBlank

class UserRegistration(
    @field:NotBlank
    val username: String = "",
    @field:NotBlank
    val password: String = ""
)