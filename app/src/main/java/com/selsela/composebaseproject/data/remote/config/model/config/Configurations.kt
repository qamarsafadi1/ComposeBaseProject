package com.selsela.composebaseproject.data.remote.config.model.config


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Configurations(
    @SerialName("address")
    val address: String = "",
    @SerialName("address_en")
    val addressEn: String = "",
    @SerialName("android")
    val android: String = "",
    @SerialName("android_version")
    val androidVersion: String = "",
    @SerialName("app_status_android")
    val appStatusAndroid: String = "",
    @SerialName("app_status_ios")
    val appStatusIos: String = "",
    @SerialName("currency_ar")
    val currencyAr: String = "",
    @SerialName("currency_en")
    val currencyEn: String = "",
    @SerialName("email")
    val email: String = "",
    @SerialName("facebook")
    val facebook: String = "",
    @SerialName("instagram")
    val instagram: String = "",
    @SerialName("ios")
    val ios: String = "",
    @SerialName("ios_version")
    val iosVersion: String = "",
    @SerialName("linked_in")
    val linkedIn: String = "",
    @SerialName("mobile")
    val mobile: String = "",
    @SerialName("name_ar")
    val nameAr: String = "",
    @SerialName("name_en")
    val nameEn: String = "",
    @SerialName("twitter")
    val twitter: String = "",
    @SerialName("update_android")
    val updateAndroid: String = "",
    @SerialName("update_ios")
    val updateIos: String = "",
    @SerialName("whatsapp")
    val whatsapp: String = ""
)