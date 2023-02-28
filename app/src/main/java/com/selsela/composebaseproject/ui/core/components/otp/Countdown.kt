package com.selsela.composebaseproject.ui.core.components.otp

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.R
import java.util.Locale

@Composable
fun Countdown(
    seconds: Long, modifier: Modifier,
    resend: () -> Unit
) {
    val millisInFuture: Long = seconds*1000

    var timeData by remember {
        mutableStateOf(millisInFuture)
    }
    var isFinish by remember {
        mutableStateOf(false)
    }

    val countDownTimer =
        object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeData = millisUntilFinished
            }

            override fun onFinish() {
                isFinish = true
            }
        }

    DisposableEffect(key1 = "key") {
        countDownTimer.start()
        onDispose {
            countDownTimer.cancel()
        }
    }
    val secMilSec: Long = 1000
    val minMilSec = 60*secMilSec
    val hourMilSec = 60*minMilSec
    val dayMilSec = 24*hourMilSec
    val minutes = (timeData%dayMilSec%hourMilSec/minMilSec).toInt()
    val seconds = (timeData%dayMilSec%hourMilSec%minMilSec/secMilSec).toInt()

    Text(
        text = String.format(
            Locale.ENGLISH,
            "%02d:%02d", minutes, seconds
        ),
        color = Color.Black,
        modifier = modifier
    )

    if (isFinish) {
        Text(
            text = stringResource(id = R.string.resend),
            color = Color.Black,
            modifier = Modifier.clickable {
                resend()
                isFinish = false
                timeData = millisInFuture
                countDownTimer.start()
            }
        )
    }
}
