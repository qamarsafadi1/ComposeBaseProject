package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OrderResponse(
    @SerializedName("has_more_page")
    val hasMorePage: Boolean = false,
    @SerializedName("orders")
    val orders: List<Order> = listOf(),
    @SerializedName("response_message")
    val responseMessage: String = "",
    @SerializedName("status")
    val status: Boolean = false
)