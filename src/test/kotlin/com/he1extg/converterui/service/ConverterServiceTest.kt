package com.he1extg.converterui.service

import com.he1extg.converterui.model.ConverterFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.FileSystemResource
import org.springframework.mock.web.MockMultipartFile

@SpringBootTest
class ConverterServiceTest {

    @Autowired
    lateinit var converterService: ConverterService

    @Test
    fun `convertFile with empty TransferData`() {
        val emptyConverterFile = ConverterFile()

        val result = converterService.processFile(emptyConverterFile)

        assertThat(result).isFalse
    }

    @Test
    fun `convertFile with incorrect pdf file TransferData`() {
        val converterFile = ConverterFile().apply {
            file = MockMultipartFile("test.mp3", FileSystemResource("E:/test.mp3").inputStream.readBytes())
        }

        val result = converterService.processFile(converterFile)

        assertThat(result).isFalse
    }

    @Test
    fun `convertFile with fill-full TransferData`() {
        val converterFile = ConverterFile().apply {
            file = MockMultipartFile("test.pdf", FileSystemResource("E:/test.pdf").inputStream.readBytes())
        }

        val result = converterService.processFile(converterFile)

        assertThat(result).isTrue
    }
}