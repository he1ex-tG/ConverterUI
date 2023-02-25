package com.he1extg.converterui.service

import com.he1extg.converterui.dto.user.NewUserDTO
import com.he1extg.converterui.dto.user.UserDTO
import com.he1extg.converterui.feign.DataClient
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val dataClient: DataClient,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun addUser(username: String, password: String): UserDTO {
        val encodedPassword = passwordEncoder.encode(password)
        val newUserDTO = NewUserDTO(username, encodedPassword)
        return dataClient.addUser(newUserDTO)
    }

    override fun getUser(username: String): UserDTO {
        return dataClient.getUser(username)
    }
}