package com.selsela.composebaseproject.data.remote.config.model.payment


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class Payment(
    @SerializedName("icon_url")
    val iconUrl: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: String = ""
)