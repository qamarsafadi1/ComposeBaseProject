package com.selsela.composebaseproject.data.remote.orders.repository

import com.google.gson.Gson
import com.selsela.composebaseproject.data.remote.orders.model.OrderResponse
import com.selsela.composebaseproject.data.remote.orders.service.OrderService
import com.selsela.composebaseproject.util.handleExceptions
import com.selsela.composebaseproject.util.handleSuccess
import com.selsela.composebaseproject.util.networking.model.ErrorBase
import com.selsela.composebaseproject.util.networking.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val api: OrderService,
) {
    suspend fun getOrders(page: Int): Flow<Resource<OrderResponse>> = withContext(Dispatchers.IO) {
        val data: Flow<Resource<OrderResponse>> = try {
            val response = api.getOrders(page)
            if (response.isSuccessful) {
                handleSuccess(
                    response.body(),
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