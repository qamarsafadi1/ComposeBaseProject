package com.selsela.composebaseproject.util.networking

import android.content.Context
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.data.local.PreferenceHelper.accessToken
import com.selsela.composebaseproject.data.local.PreferenceHelper.appLocale
import com.selsela.composebaseproject.data.local.PreferenceHelper.fcmToken
import com.selsela.composebaseproject.util.handleExceptions
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        try {
            request = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .header("language", LocalData.appLocale ?: "ar")
                .header("device_key", LocalData.fcmToken ?: "")
                .header("device_type", "android")
                .apply {
                    LocalData.accessToken.let {
                        if (it.isNullOrEmpty().not())
                            addHeader("Authorization", "Bearer $it")
                    }
                }
                .build()

        } catch (e: Exception) {
            handleExceptions<Exception>(e)
        }
        return chain.proceed(request)

    }
}