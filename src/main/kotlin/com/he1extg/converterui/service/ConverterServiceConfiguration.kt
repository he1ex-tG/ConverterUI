package com.he1extg.converterui.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "converter")
class ConverterServiceConfiguration {

    lateinit var uriApi: String
    lateinit var uriData: String
}