package com.he1extg.converterui.feign

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.he1extg.converterui.exception.client.ApiClientException
import com.he1extg.converterui.exception.ApiError
import com.he1extg.converterui.exception.client.ApiClientRetryerException
import feign.RetryableException
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus

class ApiClientConfiguration {

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
            ApiClientException{
                mapper.readValue(response.body().asInputStream(), ApiError::class.java)
            }
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
                    throw ApiClientRetryerException {
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