package com.selsela.composebaseproject.ui.screens.orders.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.data.remote.orders.model.Order
import com.selsela.composebaseproject.ui.core.components.screen.EmptyView
import com.selsela.composebaseproject.ui.core.components.screen.LoadingView
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.ui.screens.orders.OrderViewModel
import com.selsela.composebaseproject.ui.screens.orders.list.item.OrderItem
import com.selsela.composebaseproject.ui.screens.orders.state.OrderUiState
import com.selsela.composebaseproject.util.Common
import com.selsela.composebaseproject.util.collectAsStateLifecycleAware
import com.selsela.composebaseproject.util.getActivity
import com.selsela.composebaseproject.util.log

@Composable
fun OrdersScreen(
    viewModel: OrderViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lazyColumnListState = rememberLazyListState()
    // check if can paginate
    val isScrollToEnd by remember {
        derivedStateOf {
            lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyColumnListState.layoutInfo.totalItemsCount - 1
                    && viewModel.canPaginate
        }
    }
    val viewState: OrderUiState by viewModel.uiState.collectAsStateLifecycleAware(
        OrderUiState()
    )

    LaunchedEffect(Unit) {
        if (!viewModel.isLoaded)
            viewModel.getOrders()
    }

    // listen if pagination allowed
    LaunchedEffect(key1 = isScrollToEnd) {
        if (isScrollToEnd)
            viewModel.getOrders()
    }

    /**
     * Handle Ui state from flow
     */

    when (viewState.state) {
        State.IDLE, State.LOADING -> LoadingView()
        State.SUCCESS,State.PAGINATING -> OrdersList(lazyColumnListState, orders = viewState.orders)
        State.ERROR -> {
            viewState.error.let {
                Common.handleErrors(
                    it?.responseMessage,
                    it?.errors,
                    context.getActivity()
                )
            }
        }
    }
}

@Composable
private fun OrdersList(lazyColumnListState: LazyListState, orders: MutableList<Order>?) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (orders.isNullOrEmpty().not()) {
            LazyColumn(
                state = lazyColumnListState,
                modifier = Modifier.fillMaxHeight()
            ) {
                items(orders ?: listOf()) {
                    OrderItem(it)
                }
            }
        } else {
            EmptyView(title = stringResource(R.string.no_orders_found))
        }
    }
}
