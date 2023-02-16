package com.selsela.composebaseproject

import android.app.Application
import android.content.SharedPreferences
import com.selsela.composebaseproject.data.local.PreferenceHelper
import com.selsela.composebaseproject.data.local.PreferenceHelper.appLocale
import com.selsela.composebaseproject.util.LocalUtils.setLocale
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    companion object {
        lateinit var LocalData: SharedPreferences
    }

    override fun onCreate() {
        LocalData = PreferenceHelper.customPreference(applicationContext, "APP_PREFERENCE")
        applicationContext.setLocale(LocalData.appLocale ?: "ar")
        super.onCreate()
    }
}