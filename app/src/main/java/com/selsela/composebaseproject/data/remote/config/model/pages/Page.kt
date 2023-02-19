package com.selsela.composebaseproject.data.remote.config.model.pages


import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Page(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("text")
    val text: String = "",
    @SerializedName("title")
    val title: String = ""
)