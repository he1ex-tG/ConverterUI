package com.he1extg.converterui.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
    private val _username: String,
    private val _password: String,
    private val _accountNonExpired: Boolean,
    private val _accountNonLocked: Boolean,
    private val _credentialsNonExpired: Boolean,
    private val _enabled: Boolean
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            GrantedAuthority{
                "ROLE_USER"
            }
        )
    }

    override fun getPassword(): String {
        return _password
    }

    override fun getUsername(): String {
        return _username
    }

    override fun isAccountNonExpired(): Boolean {
        return _accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return _accountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return _credentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return _enabled
    }
}