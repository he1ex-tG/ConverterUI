package com.he1extg.converterui.exception

import java.io.IOException

class DataClientException(
    val apiError: ApiError
) : IOException(apiError.message) {
    constructor(apiError: () -> ApiError) : this(apiError())
}