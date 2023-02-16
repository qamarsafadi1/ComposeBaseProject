package com.selsela.composebaseproject.data.remote.config.model.cities


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("childs")
    val childs: List<Child> = listOf(),
    @SerializedName("country_id")
    val countryId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("parent_id")
    val parentId: Int = 0
)