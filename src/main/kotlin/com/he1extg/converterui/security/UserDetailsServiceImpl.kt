package com.he1extg.converterui.security

import com.he1extg.converterui.dto.UsernamePasswordDTO
import com.he1extg.converterui.feign.DataClient
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val dataClient: DataClient,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    fun addUser(userDetails: UserDetails) {
        dataClient.addUser(
            UsernamePasswordDTO(
                userDetails.username,
                passwordEncoder.encode(userDetails.password)
            )
        )
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val usernamePasswordDTO = dataClient.getUser(username)
        return UserDetailsImpl(
            _username = usernamePasswordDTO.username,
            _password = usernamePasswordDTO.password
        )
    }
}