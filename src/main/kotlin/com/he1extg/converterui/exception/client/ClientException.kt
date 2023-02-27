package com.he1extg.converterui.exception.client

import com.he1extg.converterui.exception.ApiError
import java.io.IOException

abstract class ClientException(
    apiError: () -> ApiError
) : IOException() {

    override val message = apiError().message
}