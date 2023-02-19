package com.selsela.composebaseproject.data.remote.config.model.config


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ConfigResponse(
    @SerializedName("configurations")
    val configurations: Configurations = Configurations(),
    @SerializedName("response_message")
    val responseMessage: String = "",
    @SerializedName("status")
    val status: Boolean = false
)