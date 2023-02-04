package com.he1extg.converterui.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.util.LinkedMultiValueMap

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConverterFileTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `upload correct file`() {
        val requestEntity = RequestEntity.post("/")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(
                LinkedMultiValueMap<String, Any>().apply {
                    add("file", FileSystemResource("E:/test.pdf"))
                }
            )

        val answer = testRestTemplate.exchange(requestEntity, String::class.java)

        assertThat(answer.statusCode).isEqualTo(HttpStatus.FOUND)
    }

    /**
     * Add error message handling
     */
    @Test
    fun `upload incorrect file`() {
        val requestEntity = RequestEntity.post("/")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(
                LinkedMultiValueMap<String, Any>().apply {
                    add("file", FileSystemResource("E:/test.mp3"))
                }
            )

        val answer = testRestTemplate.exchange(requestEntity, String::class.java)

        assertThat(answer.statusCode).isEqualTo(HttpStatus.FOUND)
    }

    /**
     * Add error message handling
     */
    @Test
    fun `upload empty file`() {
        val requestEntity = RequestEntity.post("/")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(
                LinkedMultiValueMap<String, Any>().apply {
                    add("file", null)
                }
            )

        val answer = testRestTemplate.exchange(requestEntity, String::class.java)

        assertThat(answer.statusCode).isEqualTo(HttpStatus.OK)
    }
}