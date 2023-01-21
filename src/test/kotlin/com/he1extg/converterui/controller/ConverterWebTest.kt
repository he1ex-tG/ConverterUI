package com.he1extg.converterui.controller

import com.he1extg.converterui.dto.IdFilenameDTO
import com.he1extg.converterui.feign.DataClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.util.LinkedMultiValueMap

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConverterWebTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @MockBean
    lateinit var dataClient: DataClient

    @Test
    fun `main get not empty file list`() {
        given(dataClient.getFileList(MockitoHelper.anyObject())).willReturn(
            listOf(
                IdFilenameDTO(1, "SuperFile1"),
                IdFilenameDTO(2, "AAAbbbCCC")
            )
        )

        val requestEntity = RequestEntity.get("/").build()

        val answer = testRestTemplate.exchange(requestEntity, String::class.java)

        assertThat(answer.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(answer.body).isNotNull
        answer.body?.let {
            assertThat(it).contains("SuperFile1", "AAAbbbCCC")
        }
    }
}

/**
 * Kotlin port of Mockito AnyObject
 */
object MockitoHelper {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T = null as T
}