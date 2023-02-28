package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AcType(
    @SerializedName("ac_type")
    val acType: AcTypeX = AcTypeX(),
    @SerializedName("count")
    val count: String = ""
)