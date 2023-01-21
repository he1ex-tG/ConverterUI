package com.he1extg.converterui.feign

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.he1extg.converterui.exception.ApiClientException
import com.he1extg.converterui.exception.ConverterError
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean

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
            val converterError = mapper.readValue(response.body().asInputStream(), ConverterError::class.java)
            ApiClientException(converterError.debugMessage)
        }
    }
}