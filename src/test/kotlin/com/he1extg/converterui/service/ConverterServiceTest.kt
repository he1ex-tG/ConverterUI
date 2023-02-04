package com.he1extg.converterui.service

import com.he1extg.converterui.dto.FilenameBytearrayDTO
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.FileSystemResource

@SpringBootTest
class ConverterServiceTest {

    @Autowired
    lateinit var converterService: ConverterService

    @Test
    fun `convertFile with empty TransferData`() {
        assertThrows<Throwable> {
            converterService.processFile {
                FilenameBytearrayDTO("", byteArrayOf())
            }
        }
    }

    @Test
    fun `convertFile with incorrect pdf file TransferData`() {
        val resource = FileSystemResource("E:/test.mp3")
        assertThrows<Throwable> {
            converterService.processFile {
                FilenameBytearrayDTO(
                    resource.file.name,
                    resource.file.readBytes()
                )
            }
        }
    }

    @Test
    fun `convertFile with fill-full TransferData`() {
        val resource = FileSystemResource("E:/test.pdf")
        val result = converterService.processFile {
            FilenameBytearrayDTO(
                resource.file.name,
                resource.file.readBytes()
            )
        }
    }
}