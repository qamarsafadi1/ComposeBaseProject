package com.selsela.composebaseproject.data.remote.config.service

import com.selsela.composebaseproject.data.remote.HttpRoutes
import com.selsela.composebaseproject.data.remote.config.model.config.ConfigResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ConfigApi(private val client: HttpClient) {

    suspend fun getConfig() = client.get(HttpRoutes.GET_CONFIGURATION)


}