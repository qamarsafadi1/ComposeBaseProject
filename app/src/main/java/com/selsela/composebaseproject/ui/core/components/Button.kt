package com.selsela.composebaseproject.ui.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.ui.theme.Purple40
import com.selsela.composebaseproject.ui.theme.buttonText

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Button(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
        .width(167.dp)
        .requiredHeight(48.dp),
    icon: Int? = null,
    buttonBg: Color = Purple40,
    isEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    // ElasticView(onClick = { onClick() }) {
    Button(
        enabled = isEnabled,
        onClick = {
            onClick()
        },
        modifier = modifier,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonBg)
    ) {
        AnimatedVisibility(
            visible = isLoading.not()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title, style = buttonText,
                    textAlign = TextAlign.Center
                )
                if (icon != null) {
                    Spacer(modifier = Modifier.width(18.dp))
                    Image(
                        painter = painterResource(id = icon), contentDescription = "",
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = isLoading,
        ) {
            LottieAnimationView(
                raw = R.raw.whiteloading,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}