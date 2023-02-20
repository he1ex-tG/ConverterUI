package com.he1extg.converterui.dto.user

class AuthenticationDTO(
    val username: String,
    val password: String,
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean
)