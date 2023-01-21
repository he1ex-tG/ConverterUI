package com.he1extg.converterui.exception

import java.io.IOException

class DataClientException(
    override val message: String = "Data: Exception."
) : IOException(message)