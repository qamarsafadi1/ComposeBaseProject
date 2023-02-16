package com.selsela.composebaseproject.data.remote.config.model.pages


import com.google.gson.annotations.SerializedName

data class PagesResponse(
    @SerializedName("page")
    val page: Page = Page(),
    @SerializedName("response_message")
    val responseMessage: String = "",
    @SerializedName("status")
    val status: Boolean = false
)