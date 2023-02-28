package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OrderService(
    @SerializedName("ac_types")
    val acTypes: List<AcType> = listOf(),
    @SerializedName("is_calculated_in_total")
    val isCalculatedInTotal: Int = 0,
    @SerializedName("price")
    val price: Int = 0,
    @SerializedName("service")
    val service: Service = Service(),
    @SerializedName("service_price")
    val servicePrice: Int = 0
)