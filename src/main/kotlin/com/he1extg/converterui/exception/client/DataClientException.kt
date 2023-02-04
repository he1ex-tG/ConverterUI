package com.he1extg.converterui.exception.client

import com.he1extg.converterui.exception.ApiError

class DataClientException(
    apiError: () -> ApiError
) : ClientException(apiError)