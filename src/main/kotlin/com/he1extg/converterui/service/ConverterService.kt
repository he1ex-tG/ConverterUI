package com.he1extg.converterui.service

import com.he1extg.converterui.model.ConverterFile

interface ConverterService {

    fun getFileList(): List<String>
    fun processFile(converterFile: ConverterFile): Boolean
}