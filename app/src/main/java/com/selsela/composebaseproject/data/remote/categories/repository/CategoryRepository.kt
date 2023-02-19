package com.selsela.composebaseproject.data.remote.categories.repository

import com.google.gson.Gson
import com.selsela.composebaseproject.data.remote.categories.model.Service
import com.selsela.composebaseproject.data.remote.categories.service.CategoryService
import com.selsela.composebaseproject.util.handleExceptions
import com.selsela.composebaseproject.util.handleSuccess
import com.selsela.composebaseproject.util.networking.model.ErrorBase
import com.selsela.composebaseproject.util.networking.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: CategoryService,
) {
    suspend fun getCategories(): Flow<Resource<List<Service>>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<List<Service>>> = try {
            val response = api.getCategories()
            if (response.isSuccessful) {
                handleSuccess(
                    response.body()?.categories,
                    response.body()?.responseMessage ?: response.message()
                )
            } else {
                val gson = Gson()
                val errorBase = gson.fromJson(response.errorBody()?.string(), ErrorBase::class.java)
                errorBase.statusCode = response.code()
                handleExceptions(errorBase)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            handleExceptions(e)
        }
        data
    }
}