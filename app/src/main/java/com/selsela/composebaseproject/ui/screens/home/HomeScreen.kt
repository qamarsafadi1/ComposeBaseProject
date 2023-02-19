package com.selsela.composebaseproject.ui.screens.home

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.data.local.PreferenceHelper.configurations
import com.selsela.composebaseproject.ui.core.components.Button
import com.selsela.composebaseproject.util.log

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDataClick: () -> Unit,
    onAuthClick: () -> Unit,
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onDataClick,
                title = "Data"
            )
            if (viewModel.userLoggedIn.value.not()) {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onAuthClick,
                    title = "Auth"
                )
            }
        }
    }
}