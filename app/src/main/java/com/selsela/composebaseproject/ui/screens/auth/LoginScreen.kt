package com.selsela.composebaseproject.ui.screens.auth

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.ui.core.AuthViewModel
import com.selsela.composebaseproject.ui.core.components.Button
import com.selsela.composebaseproject.ui.core.components.InputText
import com.selsela.composebaseproject.util.InputWrapper

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {

    val mobile by viewModel.mobile.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val areInputsValid by viewModel.areInputsValid.collectAsStateWithLifecycle()

    LoginScreenContent(mobile, viewModel, password, areInputsValid)
}

@Composable
private fun LoginScreenContent(
    mobile: InputWrapper,
    viewModel: AuthViewModel,
    password: InputWrapper,
    areInputsValid: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            InputText(
                text = mobile.value,
                onValueChange = viewModel::onMobileEntered,
                hint = stringResource(R.string.mobile),
                isValid = mobile.validationMessage.isNullOrEmpty(),
                validationMessage = mobile.validationMessage ?: "",
                inputType = KeyboardType.Phone,
                borderColor = mobile.borderColor,
            )

            Spacer(modifier = Modifier.height(30.dp))

            InputText(
                text = password.value,
                onValueChange = viewModel::onPasswordEntered,
                hint = stringResource(R.string.password),
                isValid = password.validationMessage.isNullOrEmpty(),
                validationMessage = password.validationMessage ?: "",
                inputType = KeyboardType.Phone,
                borderColor = password.borderColor,
            )

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = viewModel::onContinueClick, isEnabled = areInputsValid,
                title = stringResource(id = R.string.login)
            )
        }
    }
}