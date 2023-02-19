package com.selsela.composebaseproject.data.remote.auth.service

import com.selsela.composebaseproject.data.remote.auth.model.AuthResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @POST("user/auth_mobile_only")
    @JvmSuppressWildcards
    @FormUrlEncoded
    suspend fun auth(
        @FieldMap
        body: Map<String, Any>
    ): Response<AuthResponse>

}