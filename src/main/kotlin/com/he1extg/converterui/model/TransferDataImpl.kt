package com.he1extg.converterui.model

class TransferData {
    var content: ByteArray? = null
    val contentSize: Int
        get() = content?.size ?: 0
}