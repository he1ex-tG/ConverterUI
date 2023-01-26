package com.he1extg.converterui.service

import com.he1extg.converterui.dto.FilenameBytearrayDTO
import com.he1extg.converterui.dto.IdFilenameDTO

interface ConverterService {

    fun getFileList(): List<IdFilenameDTO>
    fun processFile(converterFile: () -> FilenameBytearrayDTO)
    fun downloadFile(id: Long): FilenameBytearrayDTO
}