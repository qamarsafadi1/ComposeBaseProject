package com.selsela.composebaseproject.util.networking.model

import androidx.annotation.Keep
import com.selsela.composebaseproject.util.networking.model.Errors

@Keep
data class ErrorsData(
    val errors: List<Errors>?= listOf(),
    val responseMessage: String? =null,
    val status: Int? = 0
)