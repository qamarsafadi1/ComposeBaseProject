package com.selsela.composebaseproject.ui.screens.home

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.data.local.PreferenceHelper.appLocale
import com.selsela.composebaseproject.data.local.PreferenceHelper.configurations
import com.selsela.composebaseproject.ui.core.components.Button
import com.selsela.composebaseproject.ui.core.components.language.LanguageSheet
import com.selsela.composebaseproject.util.LocalUtils.setLocale
import com.selsela.composebaseproject.util.log
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDataClick: () -> Unit,
    onChangeLanguageClick: () -> Unit,
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
                title = stringResource(R.string.data)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onChangeLanguageClick,
                title = stringResource(R.string.change_langauge)
            )
            if (viewModel.userLoggedIn.value.not()) {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onAuthClick,
                    title = stringResource(R.string.auth)
                )
            } else {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onChangeLanguageClick,
                    title = stringResource(R.string.pagination_data)
                )
            }
        }
    }
}