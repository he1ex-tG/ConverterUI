package com.he1extg.converterui.dto

class FileUploadDTO(
    val content: ByteArray,
    val filename: String,
    val username: String,
) {
    val contentSize: Int
        get() = content.size
}