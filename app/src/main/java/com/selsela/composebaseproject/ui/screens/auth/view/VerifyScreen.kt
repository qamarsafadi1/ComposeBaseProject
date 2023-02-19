package com.selsela.composebaseproject.ui.screens.auth.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.ui.core.components.otp.OtpTextField
import com.selsela.composebaseproject.ui.core.components.otp.Countdown
import com.selsela.composebaseproject.ui.screens.auth.AuthViewModel
import com.selsela.composebaseproject.ui.theme.Purple40
import com.selsela.composebaseproject.ui.theme.text11
import com.selsela.composebaseproject.ui.theme.text12
import com.selsela.composebaseproject.ui.theme.text18
import com.selsela.composebaseproject.util.InputWrapper

@Composable
fun VerifyScreen(
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val code by viewModel.code.collectAsStateWithLifecycle()
    val mobile by viewModel.mobile.collectAsStateWithLifecycle()
    val isCodeValid by viewModel.isCodeValid.collectAsStateWithLifecycle()

    VerifyScreenContent(mobile,code, isCodeValid,viewModel){}
}

@Composable
private fun VerifyScreenContent(
    mobile: InputWrapper,
    code: InputWrapper,
    isCodeValid: Boolean,
    viewModel: AuthViewModel,
    onConfirm: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 73.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.verify_code),
                style = text18,
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.pleaes_enter_code),
                style = text11,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "+966${mobile.inputValue}",
                style = text18,
                color = Purple40,
                modifier = Modifier.padding(top = 17.dp)

            )

            OtpTextField(
                otpText = code,
                onOtpTextChange = viewModel::onCodeEntered,
                modifier = Modifier.padding(top = 25.dp),
            )

            Text(
                text = stringResource(R.string.you_will_recive_code_in),
                style = text12,
                color = Color.White,
                modifier = Modifier.padding(top = 20.dp)
            )
            Countdown(
                30,
                modifier = Modifier.padding(8.dp)
            ) {
                //  resend()
            }

            com.selsela.composebaseproject.ui.core.components.Button(
                onClick = onConfirm,
                title = stringResource(R.string.confirm),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .requiredHeight(48.dp),
                isEnabled = isCodeValid
                // isLoading = uiState.isLoading
            )
        }
    }
}