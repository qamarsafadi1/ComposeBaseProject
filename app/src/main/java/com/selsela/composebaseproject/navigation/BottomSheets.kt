package com.selsela.composebaseproject.navigation

sealed class BottomSheetScreen() {
    object LangaugeSheet : BottomSheetScreen()
}

sealed class SheetActions(){
    object OnConfirm: SheetActions()
    object Close: SheetActions()
}