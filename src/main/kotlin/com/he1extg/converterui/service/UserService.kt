package com.he1extg.converterui.service

import com.he1extg.converterui.dto.user.NewUserDTO
import com.he1extg.converterui.dto.user.UserDTO

interface UserService {

    fun addUser(block: () -> NewUserDTO): UserDTO

    fun getUser(username: String): UserDTO
}