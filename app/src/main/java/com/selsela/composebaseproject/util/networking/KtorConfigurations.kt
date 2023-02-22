package com.selsela.composebaseproject.util.networking

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.selsela.composebaseproject.BaseApp.Companion.LocalData
import com.selsela.composebaseproject.data.local.PreferenceHelper.appLocale
import com.selsela.composebaseproject.data.local.PreferenceHelper.fcmToken
import com.selsela.composebaseproject.util.log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import java.util.concurrent.TimeUnit


// val ktorHttpClient = HttpClient(Android) {
//
//    install(ContentNegotiation) {
//        json()
//        engine {
//            connectTimeout = TIME_OUT
//            socketTimeout = TIME_OUT
//        }
//    }
//
//    install(Logging) {
//        logger = object : Logger {
//            override fun log(message: String) {
//                message.log()
//            }
//
//        }
//        level = LogLevel.ALL
//    }
//
//    install(ResponseObserver) {
//        onResponse { response ->
//            response.log()
//        }
//    }
//
//    install(DefaultRequest) {
//        header(HttpHeaders.ContentType, ContentType.Application.Json)
//        header(HttpHeaders.AccessControlAllowOrigin, ContentType.Application.Json)
//        header(HttpHeaders.Accept, ContentType.Application.Json)
//        header("language",  LocalData.appLocale ?: "ar")
//        header("device_key",  LocalData.fcmToken ?: "")
//        header("device_type", "android")
//    }
//}
private val TIME_OUT = 80*1000L

val ktorHttpClient = HttpClient(OkHttp) {
    var okHttpClientBuilder: OkHttpClient.Builder? = null

    defaultRequest {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        header(HttpHeaders.Accept, ContentType.Application.Json)
        header(HttpHeaders.AccessControlAllowOrigin, "*")

    }
    engine {
        // this: OkHttpConfig
        config {
            okHttpClientBuilder = OkHttpClient.Builder().apply {
                connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                followRedirects(true)
            }

            val interceptor = LoggingInterceptor.Builder()
                .setLevel(Level.BODY)
                .log(Platform.INFO)
                .request("|==Req==|  ")
                .response("|==Response==|  ")
                .build()
            addInterceptor(interceptor)
            addNetworkInterceptor(interceptor)

            preconfigured = okHttpClientBuilder?.build()
        }
    }
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
            }
        )
    }
}

