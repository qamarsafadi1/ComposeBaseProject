package com.selsela.composebaseproject.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.ui.theme.ComposeBaseProjectTheme
import com.selsela.composebaseproject.util.receiveToken
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: ConfigViewModel = hiltViewModel(),
    onFinish: () -> Unit
) {
    SplashContent(onFinish)

    LaunchedEffect(Unit) {
        /**
         * Get fcm token
         */
        receiveToken()
    }
}


@Composable
private fun SplashContent(onFinish: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        val scale = remember {
            Animatable(0.0f)
        }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(800, easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
            )
            delay(4000)
            onFinish()
        }

        Image(
            painter = painterResource(id = R.drawable.logo_w),
            contentDescription = "logo",
            modifier = Modifier
                .size(200.dp)
                .scale(
                    scale.value
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    ComposeBaseProjectTheme {
        SplashScreen {
        }
    }
}