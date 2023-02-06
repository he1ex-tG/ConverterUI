package com.he1extg.converterui.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class UserDetailsImpl(
    private val _username: String,
    private val _password: String
) : UserDetails {

    private val passwordEncoder: PasswordEncoder
        get() {
            val idForEncode = "bcrypt"
            val encoders: Map<String, PasswordEncoder> = mapOf(
                idForEncode to BCryptPasswordEncoder(12)
            )
            return DelegatingPasswordEncoder(idForEncode, encoders)
        }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            GrantedAuthority{
                "ROLE_USER"
            }
        )
    }

    override fun getPassword(): String {
        return passwordEncoder.encode(_password)
    }

    override fun getUsername(): String {
        return _username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}