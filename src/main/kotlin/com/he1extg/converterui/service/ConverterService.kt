package com.he1extg.converterui.service

import com.he1extg.converterui.dto.file.ContentDTO
import com.he1extg.converterui.dto.file.FileUploadDTO
import com.he1extg.converterui.dto.file.FilenameDTO

interface ConverterService {

    fun getFileList(userId: Long): List<FilenameDTO>
    fun processFile(block: () -> FileUploadDTO)
    fun downloadFile(fileId: Long): ContentDTO
}