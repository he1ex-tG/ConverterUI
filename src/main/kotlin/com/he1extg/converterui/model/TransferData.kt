package com.he1extg.converterui.model

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
interface TransferData {
    val content: ByteArray?
    val contentSize: Int
}