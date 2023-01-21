package com.he1extg.converterui.exception

class ConverterValidationError(
    private val `object`: String,
    private val message: String,
) : ConverterSubError() {

    private var field: String? = null
    private var rejectedValue: Any? = null

    constructor(
        `object`: String,
        field: String,
        rejectedValue: Any,
        message: String
    ) : this(`object`, message) {
        this.field = field
        this.rejectedValue = rejectedValue
    }

}