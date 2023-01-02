package com.he1extg.converterui.dto

class TransferData {
    var content: ByteArray? = null
    val contentSize: Int
        get() = content?.size ?: 0
    var name: String? = null
    var user: String? = null
}