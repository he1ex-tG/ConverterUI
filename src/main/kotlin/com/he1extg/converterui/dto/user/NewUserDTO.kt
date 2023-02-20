package com.he1extg.converterui.dto.user

import javax.validation.constraints.NotBlank

class NewUserDTO(
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String
)