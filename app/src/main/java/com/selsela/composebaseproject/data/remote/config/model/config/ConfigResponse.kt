package com.selsela.composebaseproject.data.remote.config.model.config


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ConfigResponse(
    @SerialName("configurations")
    val configurations: Configurations = Configurations(),
    @SerialName("response_message")
    val responseMessage: String = "",
    @SerialName("status")
    val status: Boolean = false
)