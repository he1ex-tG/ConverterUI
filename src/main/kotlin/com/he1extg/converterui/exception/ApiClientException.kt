package com.he1extg.converterui.exception

import java.io.IOException

class ApiClientException(
    val apiError: ApiError
) : IOException(apiError.message)