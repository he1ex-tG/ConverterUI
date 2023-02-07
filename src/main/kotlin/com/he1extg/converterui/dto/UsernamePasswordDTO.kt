package com.he1extg.converterui.dto

import javax.validation.constraints.NotBlank

class UsernamePasswordDTO(
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String
)