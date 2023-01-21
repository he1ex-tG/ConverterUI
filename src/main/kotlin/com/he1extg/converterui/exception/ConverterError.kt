package com.he1extg.converterui.exception

import org.springframework.http.HttpStatus
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ConverterError(
    val status: HttpStatus,
    val message: String,
    val timestamp: String = DateTimeFormatter
        .ofPattern("HH:mm:ss.SSSSSS dd.MM.yyyy")
        .withZone(ZoneOffset.UTC)
        .format(Instant.now()),
    val debugMessage: String = "",
    val subErrors: List<ConverterSubError>? = null,
) {
    constructor(
        status: HttpStatus,
        message: String,
        ex: Throwable
    ) : this(
        status = status,
        message = message,
        debugMessage = ex.localizedMessage
    )
}