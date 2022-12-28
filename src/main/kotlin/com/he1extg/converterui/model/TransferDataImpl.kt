package com.he1extg.converterui.model

class TransferDataImpl : TransferData {
    override var content: ByteArray? = null
    override val contentSize: Int
        get() = content?.size ?: 0
}