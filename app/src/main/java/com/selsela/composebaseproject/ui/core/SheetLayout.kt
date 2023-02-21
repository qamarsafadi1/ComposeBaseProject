package com.selsela.composebaseproject.ui.core

import androidx.compose.runtime.Composable
import com.selsela.composebaseproject.navigation.BottomSheetScreen
import com.selsela.composebaseproject.ui.core.components.language.LanguageSheet
import com.selsela.composebaseproject.ui.core.sheet.BottomSheetWithCloseDialog

@Composable
fun SheetLayout(currentScreen: BottomSheetScreen, onCloseBottomSheet :()->Unit) {
    BottomSheetWithCloseDialog(onCloseBottomSheet){
        when(currentScreen){
            BottomSheetScreen.LangaugeSheet -> LanguageSheet(onCloseBottomSheet)
          }
    }
}