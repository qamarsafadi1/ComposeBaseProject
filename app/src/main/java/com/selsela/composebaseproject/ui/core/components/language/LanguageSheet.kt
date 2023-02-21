package com.selsela.composebaseproject.ui.core.components.language

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.data.local.PreferenceHelper.appLocale
import com.selsela.composebaseproject.ui.core.components.Button
import com.selsela.composebaseproject.ui.core.components.language.item.LanguageItem
import com.selsela.composebaseproject.ui.theme.text18
import com.selsela.composebaseproject.util.LocalUtils.setLocale

@Composable
fun LanguageSheet(onClose: () -> Unit,
) {
    Box {
        val context = LocalContext.current

        var check by remember {
            if (LocalData.appLocale == "ar")
                mutableStateOf(0)
            else mutableStateOf(1)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .padding(
                    horizontal = 24.dp,
                    vertical = 46.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_language),
                style = text18,
                color = Color.White
            )

            LanguageItem(check) {
                check = it
                LocalData.appLocale = if (check == 0) "ar" else "end"
            }
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                onClick = {
                    if (check == 0) {
                        context.setLocale("ar")
                    } else {
                        context.setLocale("en")
                    }
                    onClose()
                },
                title = stringResource(R.string.confirm_lbl),
            )
        }
    }

}