package com.selsela.composebaseproject.data.remote.config.model.pages


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PagesResponse(
    @SerializedName("page")
    val page: Page = Page(),
    @SerializedName("response_message")
    val responseMessage: String = "",
    @SerializedName("status")
    val status: Boolean = false
)