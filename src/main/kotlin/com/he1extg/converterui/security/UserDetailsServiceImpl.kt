package com.he1extg.converterui.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {

    private val inMemoryUserDetailsManager = InMemoryUserDetailsManager().apply {
        createUser(User("aaa", passwordEncoder.encode("aaa")))
        createUser(User("bbb", passwordEncoder.encode("bbb")))
    }

    fun addUser(userDetails: UserDetails) {
        inMemoryUserDetailsManager.createUser(userDetails)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return inMemoryUserDetailsManager.loadUserByUsername(username)
    }
}