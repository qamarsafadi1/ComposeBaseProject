package com.selsela.composebaseproject.ui.screens.auth.view

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.ui.core.components.Button
import com.selsela.composebaseproject.ui.core.components.InputText
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.ui.screens.auth.AuthViewModel
import com.selsela.composebaseproject.ui.screens.auth.state.AuthUiState
import com.selsela.composebaseproject.util.Common
import com.selsela.composebaseproject.util.InputWrapper
import com.selsela.composebaseproject.util.collectAsStateLifecycleAware
import com.selsela.composebaseproject.util.getActivity
import com.selsela.composebaseproject.util.toJson
import de.palm.composestateevents.EventEffect

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    goToHome: () -> Unit,
    goToVerify: () -> Unit,
) {

    val mobile by viewModel.mobile.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val areInputsValid by viewModel.areInputsValid.collectAsStateWithLifecycle()
    val viewState: AuthUiState by viewModel.uiState.collectAsStateLifecycleAware(AuthUiState())
    val context = LocalContext.current

    LoginScreenContent(
        viewState,
        mobile, viewModel, password, areInputsValid,
        onLoginClick = viewModel::login
    )

    /**
     * Handle Ui state from flow
     */

    EventEffect(
        event = viewState.onSuccess,
        onConsumed = viewModel::onSuccess
    ) {
        goToHome()
    }

    EventEffect(
        event = viewState.onVerify,
        onConsumed = viewModel::onVerify
    ) {
        goToVerify()
    }


    EventEffect(
        event = viewState.onFailure,
        onConsumed = viewModel::onFailure
    ) { error ->
        Common.handleErrors(
            error.responseMessage,
            error.errors,
            context.getActivity()
        )
    }
}

@Composable
private fun LoginScreenContent(
    viewState: AuthUiState,
    mobile: InputWrapper,
    viewModel: AuthViewModel,
    password: InputWrapper,
    areInputsValid: Boolean,
    onLoginClick: () -> Unit
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
                text = mobile.inputValue,
                onValueChange = viewModel::onMobileEntered,
                hint = stringResource(R.string.mobile),
                isValid = mobile.validationMessage.isNullOrEmpty(),
                validationMessage = mobile.validationMessage ?: "",
                inputType = KeyboardType.Phone,
                borderColor = mobile.borderColor,
            )

            Spacer(modifier = Modifier.height(30.dp))

            InputText(
                text = password.inputValue,
                onValueChange = viewModel::onPasswordEntered,
                hint = stringResource(R.string.password),
                isValid = password.validationMessage.isNullOrEmpty(),
                validationMessage = password.validationMessage ?: "",
                inputType = KeyboardType.Password,
                borderColor = password.borderColor,
            )

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = onLoginClick, isEnabled = areInputsValid,
                title = stringResource(id = R.string.login),
                isLoading = areInputsValid && viewState.state == State.LOADING
            )
        }
    }
}