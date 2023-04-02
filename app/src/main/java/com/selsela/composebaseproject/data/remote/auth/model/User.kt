package com.selsela.composebaseproject.data.remote.auth.model


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
import com.selsela.composebaseproject.BaseApp
import com.selsela.composebaseproject.data.local.PreferenceHelper.accessToken
import com.selsela.composebaseproject.data.local.PreferenceHelper.user

@Keep
data class User(
    @SerializedName("accessToken")
    val accessToken: String = "",
    @SerializedName("activation_code")
    val activationCode: String = "",
    @SerializedName("avatar")
    val avatar: String = "",
    @SerializedName("country")
    val country: Country = Country(),
    @SerializedName("country_id")
    val countryId: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("enable_notification")
    val enableNotification: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("language")
    val language: String = "",
    @SerializedName("mobile")
    val mobile: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("schedule_notification_before")
    val scheduleNotificationBefore: Int = 0,
    @SerializedName("status")
    val status: String = ""
){
    companion object{
        var CurrentUser : User?
            get() {
                return BaseApp.LocalData.user
            }
            set(value) {
                BaseApp.LocalData.user = value
            }
        var AccessToken : String?
            get() {
                return BaseApp.LocalData.accessToken
            }
            set(value) {
                BaseApp.LocalData.accessToken = value
            }
    }
}