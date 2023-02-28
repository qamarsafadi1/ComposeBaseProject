package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Case(
    @SerializedName("can_cancel")
    val canCancel: Int = 0,
    @SerializedName("can_rate")
    val canRate: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)