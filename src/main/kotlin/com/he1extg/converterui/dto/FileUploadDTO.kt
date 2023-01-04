package com.he1extg.converterui.dto

class FileUploadDTO(
    val content: ByteArray,
    val name: String,
    val user: String,
) {
    val contentSize: Int
        get() = content.size
}