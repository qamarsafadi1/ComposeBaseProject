package com.selsela.composebaseproject.ui.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.selsela.composebaseproject.util.InputWrapper

@Composable
fun CustomTextField(
    inputWrapper: InputWrapper,
    @StringRes labelResId: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = remember {
        KeyboardOptions.Default
    },
    onValueChange: (String) -> Unit,
    onImeKeyAction: (() -> Unit)? = null

) {
    Column {
        InputText(
            modifier = modifier,
            text = inputWrapper.value,
            onValueChange = {
                onValueChange(inputWrapper.value)
            },
            hint =stringResource(labelResId),
            isValid = inputWrapper.validationMessage != null,
            validationMessage = inputWrapper.validationMessage ?: "",
            inputType = keyboardOptions.keyboardType,
            keyboardActions = remember {
                KeyboardActions(onAny = {
                    if (onImeKeyAction != null)
                        onImeKeyAction()
                })
            },
        )
    }
}