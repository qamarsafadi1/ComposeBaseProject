package com.selsela.composebaseproject.data.remote.orders.service

import com.selsela.composebaseproject.data.remote.orders.model.OrderResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OrderService {
    @GET("user/order/user_orders")
    suspend fun getOrders(
        @Query("page") page: Int,
        @Query("case_id") caseId: Int = 5
    ): Response<OrderResponse>
}