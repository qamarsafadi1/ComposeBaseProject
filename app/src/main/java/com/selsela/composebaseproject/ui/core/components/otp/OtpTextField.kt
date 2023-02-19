package com.selsela.composebaseproject.ui.core.components.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.ui.theme.text12
import com.selsela.composebaseproject.util.InputWrapper

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: InputWrapper,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText.inputValue,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it, it.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(otpCount) { index ->
                    OtpView(
                        index = index,
                        text = otpText.inputValue,
                        borderColor = otpText.borderColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        },
    )

    AnimatedVisibility(visible =  otpText.validationMessage.isNullOrEmpty()) {
        Text(
            text = otpText.validationMessage ?: "",
            style = text12,
            color = Red,
            modifier = Modifier.padding( top = 12.dp)
        )
    }
}