package com.selsela.composebaseproject.ui.screens.orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selsela.composebaseproject.data.remote.orders.model.Order
import com.selsela.composebaseproject.data.remote.orders.repository.OrderRepository
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.ui.core.state.UiState
import com.selsela.composebaseproject.util.networking.model.ErrorsData
import com.selsela.composebaseproject.util.networking.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {

    var isLoaded = false
    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    private val orderList = mutableStateListOf<Order>()

    /**
     * State Flows
     */
    private val _uiState = MutableStateFlow(UiState<Order>())
    val uiState: StateFlow<UiState<Order>> = _uiState.asStateFlow()
    private var state: UiState<Order>
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }


    /**
     * API Requests
     */
    fun getOrders() {
        viewModelScope.launch {
            state = state.copy(
                state = if (page == 1) State.LOADING else State.PAGINATING
            )
            repository.getOrders(page)
                .collect { result ->
                    val orderUiState = when (result.status) {
                        Status.SUCCESS -> {
                            isLoaded = true
                            canPaginate = result.data?.hasMorePage ?: false
                            if (page == 1) {
                                orderList.clear()
                                result.data?.orders?.let { orderList.addAll(it) }
                            } else {
                                result.data?.orders?.let { orderList.addAll(it) }
                            }
                            if (canPaginate)
                                page++
                            UiState<Order>(
                                state = State.SUCCESS,
                                dataList = orderList
                            )
                        }
                        Status.LOADING ->
                            UiState<Order>(
                                state = if (page == 1) State.LOADING else State.PAGINATING
                            )

                        Status.ERROR -> UiState<Order>(
                            state = State.ERROR,
                            error = ErrorsData(
                                result.errors,
                                result.message,
                                result.statusCode
                            )
                        )
                    }
                    state = orderUiState
                }
        }
    }
}