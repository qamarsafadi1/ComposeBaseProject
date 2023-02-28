package com.selsela.composebaseproject.ui.core

import android.content.Context
import androidx.compose.runtime.Composable
import com.selsela.composebaseproject.navigation.BottomSheetScreen
import com.selsela.composebaseproject.ui.core.components.language.LanguageSheet
import com.selsela.composebaseproject.ui.core.sheet.BottomSheetWithCloseDialog
import com.selsela.composebaseproject.util.LocalUtils.setLocale
import com.selsela.composebaseproject.util.withDelay

@Composable
fun SheetLayout(context: Context, currentScreen: BottomSheetScreen, onCloseBottomSheet: () -> Unit) {
    BottomSheetWithCloseDialog(onCloseBottomSheet) {
        when (currentScreen) {
            BottomSheetScreen.LangaugeSheet -> LanguageSheet {
                { context.setLocale(it) }.withDelay(500)
                onCloseBottomSheet();
            }
        }
    }
}