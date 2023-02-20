package com.he1extg.converterui.security

import com.he1extg.converterui.feign.DataClient
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val dataClient: DataClient
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val authenticationDTO = dataClient.getUserAuthentication(username)
        return UserDetailsImpl(
            _username = authenticationDTO.username,
            _password = authenticationDTO.password,
            _accountNonExpired = authenticationDTO.accountNonExpired,
            _accountNonLocked = authenticationDTO.accountNonLocked,
            _credentialsNonExpired = authenticationDTO.credentialsNonExpired,
            _enabled = authenticationDTO.enabled
        )
    }
}