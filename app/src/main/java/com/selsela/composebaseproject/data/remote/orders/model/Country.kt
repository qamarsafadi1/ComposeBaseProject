package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Country(
    @SerializedName("currency")
    val currency: String = "",
    @SerializedName("flag_url")
    val flagUrl: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("mobile_digits")
    val mobileDigits: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("nationality")
    val nationality: String = "",
    @SerializedName("prefix")
    val prefix: Int = 0
)