package com.he1extg.converterui.feign

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.he1extg.converterui.exception.ApiError
import com.he1extg.converterui.exception.DataClientException
import feign.RetryableException
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus

class DataClientConfiguration {

    @Bean
    fun errorDecoder(): ErrorDecoder {
        return ErrorDecoder { _, response ->
            val mapper = ObjectMapper().registerModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            )
            val apiError = mapper.readValue(response.body().asInputStream(), ApiError::class.java)
            DataClientException(apiError)
        }
    }

    @Bean
    fun retryer(): Retryer {
        return object : Retryer {
            override fun clone(): Retryer {
                return this
            }

            override fun continueOrPropagate(e: RetryableException?) {
                e?.let {
                    throw DataClientException {
                        val errorMessage = "Unable to get a response from service."
                        ApiError(
                            HttpStatus.SERVICE_UNAVAILABLE,
                            errorMessage,
                            e
                        )
                    }
                }
            }
        }
    }
}