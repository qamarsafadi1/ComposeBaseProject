package com.selsela.composebaseproject.data.remote.categories.model


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Service(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image_utl")
    val imageUtl: String = "",
    @SerializedName("multiple_count")
    val multipleCount: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: Int = 0
)