package com.he1extg.converterui.dto.user

import com.he1extg.converterui.dto.file.FilenameDTO

class UserDTO(
    val id: Long,
    val username: String,
    val files: List<FilenameDTO>
)