package com.selsela.composebaseproject.data.remote.config.model.payment


import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("payments")
    val payments: List<Payment> = listOf(),
    @SerializedName("response_message")
    val responseMessage: String = "",
    @SerializedName("status")
    val status: Boolean = false
)