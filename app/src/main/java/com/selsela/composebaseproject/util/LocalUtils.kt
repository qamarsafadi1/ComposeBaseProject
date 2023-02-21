package com.selsela.composebaseproject.util

import android.content.Context
import android.icu.util.LocaleData
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.data.local.PreferenceHelper.appLocale

object LocalUtils {
    fun Context.setLocale(lang: String) {
        LocalData.appLocale = lang
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(lang)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}