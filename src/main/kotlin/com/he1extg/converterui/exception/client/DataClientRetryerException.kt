package com.he1extg.converterui.exception.client

import com.he1extg.converterui.exception.ApiError

class DataClientRetryerException(
    apiError: () -> ApiError
) : ClientException(apiError)