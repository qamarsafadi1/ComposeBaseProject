package com.selsela.composebaseproject.data.remote.config.service

import com.selsela.composebaseproject.data.remote.config.model.cities.CitiesResponse
import com.selsela.composebaseproject.data.remote.config.model.config.ConfigResponse
import com.selsela.composebaseproject.data.remote.config.model.pages.PagesResponse
import com.selsela.composebaseproject.data.remote.config.model.payment.PaymentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConfigServiceApi {

    @GET("app/get_configuration")
    suspend fun getConfigurations(): Response<ConfigResponse>

    @GET("app/get_payments")
    suspend fun getPaymentsType(): Response<PaymentResponse>

    @GET("app/get_cities")
    suspend fun getCities(
        @Query("country_id") countryId: Int
    ): Response<CitiesResponse>

    @GET("app/page/{id}")
    suspend fun getPages(
        @Path("id") id: Int
    ): Response<PagesResponse>

}