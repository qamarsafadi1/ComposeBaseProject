package com.selsela.composebaseproject.data.remote.config.model.config


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
import com.selsela.composebaseproject.BaseApp
import com.selsela.composebaseproject.data.local.PreferenceHelper.appLocale
import com.selsela.composebaseproject.data.local.PreferenceHelper.configurations

@Keep
data class Configurations(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("address_en")
    val addressEn: String = "",
    @SerializedName("android")
    val android: String = "",
    @SerializedName("android_version")
    val androidVersion: String = "",
    @SerializedName("app_status_android")
    val appStatusAndroid: String = "",
    @SerializedName("app_status_ios")
    val appStatusIos: String = "",
    @SerializedName("currency_ar")
    val currencyAr: String = "",
    @SerializedName("currency_en")
    val currencyEn: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("facebook")
    val facebook: String = "",
    @SerializedName("instagram")
    val instagram: String = "",
    @SerializedName("ios")
    val ios: String = "",
    @SerializedName("ios_version")
    val iosVersion: String = "",
    @SerializedName("linked_in")
    val linkedIn: String = "",
    @SerializedName("mobile")
    val mobile: String = "",
    @SerializedName("name_ar")
    val nameAr: String = "",
    @SerializedName("name_en")
    val nameEn: String = "",
    @SerializedName("twitter")
    val twitter: String = "",
    @SerializedName("update_android")
    val updateAndroid: String = "",
    @SerializedName("update_ios")
    val updateIos: String = "",
    @SerializedName("whatsapp")
    val whatsapp: String = ""
){
    companion object{
        var Config : Configurations?
            get() {
                return BaseApp.LocalData.configurations
            }
            set(value) {
                BaseApp.LocalData.configurations = value
            }

        val Currency : String?
            get() {
                return if(BaseApp.LocalData.appLocale == "ar") BaseApp.LocalData.configurations?.currencyAr else BaseApp.LocalData.configurations?.currencyEn
            }
    }
}