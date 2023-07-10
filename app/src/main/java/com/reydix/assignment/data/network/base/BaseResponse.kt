package com.reydix.assignment.data.network.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseResponse {
    var isError: Int? = null
    var message: String = ""
}
