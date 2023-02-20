package com.he1extg.converterui.service

import com.he1extg.converterui.dto.user.NewUserDTO
import com.he1extg.converterui.dto.user.UserDTO
import com.he1extg.converterui.feign.DataClient
import org.springframework.security.crypto.password.PasswordEncoder

class UserServiceImpl(
    private val dataClient: DataClient,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun addUser(block: () -> NewUserDTO): UserDTO {
        return dataClient.addUser {
            block().apply {
                passwordEncoder.encode(password)
            }
        }
    }

    override fun getUser(username: String): UserDTO {
        return dataClient.getUser(username)
    }
}