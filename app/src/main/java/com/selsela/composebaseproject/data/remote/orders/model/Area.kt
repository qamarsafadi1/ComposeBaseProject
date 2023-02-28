package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Area(
    @SerializedName("country_id")
    val countryId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("orders_count")
    val ordersCount: Int = 0,
    @SerializedName("parent_id")
    val parentId: Int = 0
)