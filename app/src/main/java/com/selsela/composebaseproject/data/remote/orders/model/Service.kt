package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Service(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("max_order_count")
    val maxOrderCount: Int? = 0,
    @SerializedName("multiple_count")
    val multipleCount: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: Int = 0,
    @SerializedName("status")
    val status: String = ""
)