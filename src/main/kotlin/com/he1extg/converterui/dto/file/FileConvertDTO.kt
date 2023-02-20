package com.he1extg.converterui.dto.file

class FileConvertDTO(
    val content: ByteArray
) {
    val contentSize: Int
        get() = content.size
}