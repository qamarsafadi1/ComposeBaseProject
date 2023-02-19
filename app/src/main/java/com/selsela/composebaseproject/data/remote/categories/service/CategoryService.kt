package com.selsela.composebaseproject.data.remote.categories.service

import com.selsela.composebaseproject.data.remote.categories.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {

    @GET("app/get_configuration")
    suspend fun getCategories(): Response<CategoryResponse>

}