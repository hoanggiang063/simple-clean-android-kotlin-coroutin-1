package com.architecture.repository.core.model

import com.google.gson.annotations.SerializedName

class ErrorModel {
    var httpCode = 0

    @SerializedName("errorCode")
    var errorCode: String? = null

    @SerializedName("errorMessage")
    var errorMessage: String? = null
}