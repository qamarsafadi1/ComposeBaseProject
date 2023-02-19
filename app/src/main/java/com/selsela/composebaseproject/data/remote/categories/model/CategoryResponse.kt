package com.selsela.composebaseproject.data.remote.categories.model


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoryResponse(
    @SerializedName("services")
    val categories: List<Service> = listOf(),
    @SerializedName("status")
    val status: Boolean = false,
    @SerializedName("response_message")
    val responseMessage: String = "",
)