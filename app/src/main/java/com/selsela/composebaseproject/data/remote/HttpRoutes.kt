package com.selsela.composebaseproject.data.remote

import com.selsela.composebaseproject.BuildConfig

object HttpRoutes {
    private const val BASE_URL = BuildConfig.baseUrl
    const val GET_CONFIGURATION = "${BASE_URL}v1/app/get_configuration"
}