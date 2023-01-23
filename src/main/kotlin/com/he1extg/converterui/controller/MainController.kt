package com.he1extg.converterui.controller

import com.he1extg.converterui.model.ConverterFile
import com.he1extg.converterui.service.ConverterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

@Controller
@SessionAttributes("converterFile")
class MainController {

    @Autowired
    lateinit var converterService: ConverterService

    @ModelAttribute(name = "converterFile")
    fun converterFile(): ConverterFile {
        return ConverterFile()
    }

    @GetMapping
    fun main(@ModelAttribute converterFile: ConverterFile): String {
        converterFile.apply {
            storedFiles = converterService.getFileList()
        }
        return "index"
    }

    @GetMapping("/{id}")
    fun downloadFile(@PathVariable id: Long): ResponseEntity<Resource> {
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
        @ModelAttribute converterFile: ConverterFile,
        sessionStatus: SessionStatus
    ): String {
        /**
         * TODO File auto check
         */
        converterService.processFile(converterFile)
        sessionStatus.setComplete()
        return "redirect:/"
    }
}