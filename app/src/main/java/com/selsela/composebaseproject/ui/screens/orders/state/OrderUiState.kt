package com.selsela.composebaseproject.ui.screens.orders.state

import androidx.annotation.Keep
import com.selsela.composebaseproject.data.remote.categories.model.Service
import com.selsela.composebaseproject.data.remote.orders.model.Order
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.util.networking.model.ErrorsData

@Keep
data class OrderUiState(
    val state: State = State.IDLE,
    val orders: MutableList<Order>? = null,
    val error: ErrorsData? = null,
)