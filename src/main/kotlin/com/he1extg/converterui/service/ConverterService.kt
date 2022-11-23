package com.he1extg.converterui.service

import org.springframework.web.multipart.MultipartFile

interface ConverterService {

    fun getFileList(): List<String>
    fun processFile(file: MultipartFile): Boolean
}