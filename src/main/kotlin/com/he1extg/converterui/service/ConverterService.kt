package com.he1extg.converterui.service

import com.he1extg.converterui.dto.FilenameBytearrayDTO
import com.he1extg.converterui.dto.IdFilenameDTO
import com.he1extg.converterui.model.ConverterFile

interface ConverterService {

    fun getFileList(): List<IdFilenameDTO>
    fun processFile(converterFile: ConverterFile)
    fun downloadFile(id: Long): FilenameBytearrayDTO
}