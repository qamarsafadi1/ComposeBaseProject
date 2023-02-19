package com.selsela.composebaseproject.ui.core.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.ui.core.components.LottieAnimationView

@Composable
fun LoadingView() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimationView(raw = R.raw.loading,
        modifier = Modifier.size(20.dp))
    }
}