package com.he1extg.converterui.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    private val inMemoryUserDetailsManager = InMemoryUserDetailsManager().apply {
        createUser(UserDetailsImpl("aaa", "aaa"))
        createUser(UserDetailsImpl("bbb", "bbb"))
    }

    fun addUser(userDetails: UserDetails) {
        inMemoryUserDetailsManager.createUser(userDetails)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return inMemoryUserDetailsManager.loadUserByUsername(username)
    }
}