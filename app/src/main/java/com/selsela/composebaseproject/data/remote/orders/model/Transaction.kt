package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Transaction(
    @SerializedName("amount")
    val amount: Int = 0,
    @SerializedName("amount_paid_from_wallet")
    val amountPaidFromWallet: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("paid_additional_from_wallet")
    val paidAdditionalFromWallet: Int = 0,
    @SerializedName("payment_type")
    val paymentType: Int = 0,
    @SerializedName("transaction_id")
    val transactionId: String = ""
)