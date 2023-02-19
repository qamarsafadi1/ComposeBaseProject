package com.selsela.composebaseproject.data.remote.config.model.cities


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CitiesResponse(
    @SerializedName("cities")
    val cities: List<City> = listOf(),
    @SerializedName("response_message")
    val responseMessage: String = "",
    @SerializedName("status")
    val status: Boolean = false
)