package com.he1extg.converterui.service

import com.he1extg.converterui.dto.user.UserDTO

interface UserService {

    fun addUser(username: String, password: String): UserDTO

    fun getUser(username: String): UserDTO
}