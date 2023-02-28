package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class WorkPeriod(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("time_from")
    val timeFrom: String = "",
    @SerializedName("time_to")
    val timeTo: String = ""
)