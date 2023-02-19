package com.selsela.composebaseproject.util

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class InputWrapper(
    val inputValue: String = "",
    val borderColor: @RawValue Color = Color.Black,
    val validationMessage: String? = null
) : Parcelable