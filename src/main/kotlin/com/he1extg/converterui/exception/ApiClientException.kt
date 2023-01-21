package com.he1extg.converterui.exception

import java.io.IOException

class ApiClientException(
    override val message: String = "Api: Exception."
) : IOException(message)