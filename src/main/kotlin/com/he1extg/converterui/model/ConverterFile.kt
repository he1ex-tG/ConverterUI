package com.he1extg.converterui.model

import com.he1extg.converterui.dto.IdFilenameDTO
import org.springframework.web.multipart.MultipartFile

class ConverterFile {
    var file: MultipartFile? = null
    var storedFiles: List<IdFilenameDTO> = listOf()
}