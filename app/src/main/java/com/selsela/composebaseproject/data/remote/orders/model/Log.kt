package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Log(
    @SerializedName("case")
    val case: Case = Case(),
    @SerializedName("case_id")
    val caseId: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("order_id")
    val orderId: Int = 0
)