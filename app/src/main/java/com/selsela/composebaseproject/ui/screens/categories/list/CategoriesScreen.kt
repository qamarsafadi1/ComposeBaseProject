package com.selsela.composebaseproject.ui.screens.categories.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.data.remote.categories.model.Service
import com.selsela.composebaseproject.ui.core.components.screen.EmptyView
import com.selsela.composebaseproject.ui.core.components.screen.LoadingView
import com.selsela.composebaseproject.ui.core.state.State
import com.selsela.composebaseproject.ui.screens.categories.CategoryViewModel
import com.selsela.composebaseproject.ui.screens.categories.list.item.CategoryItem
import com.selsela.composebaseproject.ui.screens.categories.state.CategoryUiState
import com.selsela.composebaseproject.util.Common
import com.selsela.composebaseproject.util.collectAsStateLifecycleAware
import com.selsela.composebaseproject.util.getActivity

@Composable
fun CategoriesScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
    val viewState: CategoryUiState by viewModel.uiState.collectAsStateLifecycleAware(CategoryUiState())
    val context = LocalContext.current

    /**
     * Get categories request
     */

    LaunchedEffect(Unit) {
        if (!viewModel.isLoaded)
            viewModel.getCategories()
    }

    /**
     * Handle Ui state from flow
     */

    when (viewState.state) {
        State.IDLE, State.LOADING -> LoadingView()
        State.SUCCESS -> CategoriesList(categories = viewState.categories, onClick)
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
private fun CategoriesList(categories: List<Service>?, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (categories.isNullOrEmpty().not()) {
            LazyColumn {
                itemsIndexed(categories!!,
                    key = { _, it ->
                        it.id
                    }) { index, it ->
                    CategoryItem(it) {
                        onClick(index)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        } else {
            EmptyView(title = stringResource(R.string.no_categories_found))
        }
    }
}