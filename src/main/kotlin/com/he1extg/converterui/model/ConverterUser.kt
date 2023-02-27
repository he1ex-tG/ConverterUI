package com.he1extg.converterui.model

import com.he1extg.converterui.dto.file.FilenameDTO
import com.he1extg.converterui.dto.user.UserDTO

class ConverterUser(
    val id: Long,
    val username: String,
    val files: List<FilenameDTO>
) {

    constructor(userDTO: UserDTO) : this(userDTO.id, userDTO.username, userDTO.files)
}