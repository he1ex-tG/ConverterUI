package com.he1extg.converterui.controller

import com.he1extg.converterui.dto.FilenameBytearrayDTO
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
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity

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

    @Test
    fun `downloadFile correct id - return resource`() {
        val testId = 1L
        given(dataClient.downloadFile(testId)).willReturn(
            FilenameBytearrayDTO("test.mp3", "LAME".toByteArray())
        )

        val requestEntity = RequestEntity.get("/$testId").build()

        val answer = testRestTemplate.exchange(requestEntity, Resource::class.java)

        assertThat(answer.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(answer.body).isNotNull
        answer.body?.let {
            assertThat(it.filename).isEqualTo("test.mp3")
            assertThat(it.inputStream.readBytes().decodeToString()).contains("LAME")
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