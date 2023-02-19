package com.selsela.composebaseproject.ui.core.components.otp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.ui.theme.BorderColor
import com.selsela.composebaseproject.ui.theme.TextFieldBg
import com.selsela.composebaseproject.ui.theme.text14WhiteCenter

@Composable
 fun OtpView(
    index: Int,
    text: String,
    borderColor: Color = BorderColor,
) {
    val color: Color by animateColorAsState(
        borderColor
    )
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else ->  text.reversed()[index].toString()
    }
    Box(
        modifier = Modifier
            .width(54.dp)
            .height(48.dp)
            .background(color = TextFieldBg, shape = RoundedCornerShape(11.dp))
            .border(
                1.dp, when {
                    isFocused -> color
                    else -> color
                },
                RoundedCornerShape(11.dp)
            )
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(

            text = char,
            style = text14WhiteCenter,
            textAlign = TextAlign.Center,
            color = if (isFocused) {
                Color.White
            } else {
                Color.White
            },
        )
    }

}