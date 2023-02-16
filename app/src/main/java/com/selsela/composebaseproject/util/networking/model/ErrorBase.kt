package com.selsela.composebaseproject.util.networking.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.selsela.composebaseproject.util.networking.model.Errors

@Keep
data class ErrorBase(
    val `data`: Any? = Any(),
    val errors: List<Errors>?= listOf(),
    @SerializedName("response_message")
        val responseMessage: String? =null,
    val count_error: String?=null,
    val error_type: String?=null,
    val status: Boolean,
    var statusCode: Int = 0,

    )