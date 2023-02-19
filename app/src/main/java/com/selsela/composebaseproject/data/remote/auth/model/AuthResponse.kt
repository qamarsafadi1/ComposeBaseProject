package com.selsela.composebaseproject.data.remote.auth.model


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AuthResponse(
    @SerializedName("response_message")
    val responseMessage: String = "",
    @SerializedName("status")
    val status: Boolean = false,
    @SerializedName("user")
    val user: User = User()
)