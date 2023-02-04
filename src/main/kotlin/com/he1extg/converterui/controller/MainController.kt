package com.he1extg.converterui.controller

import com.he1extg.converterui.dto.FilenameBytearrayDTO
import com.he1extg.converterui.dto.IdFilenameDTO
import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.service.ConverterService
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

@Controller
@SessionAttributes("converterFile")
class MainController(
    val converterService: ConverterService
) {
    @ModelAttribute(name = "converterFile")
    fun converterFile(): ConverterFile {
        return ConverterFile()
    }

    @ModelAttribute(name = "storedFiles")
    fun listStoredFiles(model: Model): List<IdFilenameDTO> {
        return converterService.getFileList()
    }

    @GetMapping
    fun main(): String {
        return "index"
    }

    @GetMapping("/{id}")
    fun downloadFile(
        @PathVariable id: Long
    ): ResponseEntity<Resource> {
        val filenameBytearrayDTO = converterService.downloadFile(id)
        val resource = object : ByteArrayResource(filenameBytearrayDTO.file) {
            override fun getFilename(): String {
                return filenameBytearrayDTO.fileName
            }
        }
        return ResponseEntity
            .ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${resource.filename}\""
            )
            .body(
                resource
            )
    }

    @PostMapping
    fun upload(
        @Valid
        converterFile: ConverterFile,
        errors: BindingResult,
        sessionStatus: SessionStatus,
    ): String {
        if (errors.hasErrors()) {
            return "index"
        }
        converterService.processFile {
            val multipartFile = converterFile.file!!
            FilenameBytearrayDTO(
                multipartFile.originalFilename ?: "filename",
                multipartFile.bytes
            )
        }
        sessionStatus.setComplete()
        return "redirect:/"
    }
}