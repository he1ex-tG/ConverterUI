package com.he1extg.converterui.model

import org.springframework.web.multipart.MultipartFile

class ConverterFile {
    var file: MultipartFile? = null

    val transferData: TransferData
        get() {
            return TransferData().apply {
                content = file?.bytes
            }
        }
}