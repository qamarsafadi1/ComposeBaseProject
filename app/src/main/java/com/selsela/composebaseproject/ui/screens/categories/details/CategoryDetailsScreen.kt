package com.selsela.composebaseproject.ui.screens.categories.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.data.remote.categories.model.Service
import com.selsela.composebaseproject.ui.core.components.AsyncImage
import com.selsela.composebaseproject.ui.screens.categories.CategoryViewModel
import com.selsela.composebaseproject.ui.screens.categories.state.CategoryUiState
import com.selsela.composebaseproject.ui.theme.text14White
import com.selsela.composebaseproject.util.collectAsStateLifecycleAware

@Composable
fun CategoryDetailsScreen(
    index: Int,
    viewModel: CategoryViewModel
) {
    val viewState: CategoryUiState by viewModel.uiState.collectAsStateLifecycleAware(CategoryUiState())

    CategoryDetailsContent(viewState.category)

    LaunchedEffect(key1 = Unit){
        viewModel.getCategoriesDetails(index)
    }

}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun CategoryDetailsContent(category: Service?) {
    Box(modifier = Modifier.fillMaxSize()) {
        ListItem(
            trailing = {
                AsyncImage(
                    imageUrl =category?.imageUtl ?: "",
                    modifier = Modifier.size(60.dp)
                )
            }
        ) {
            Text(
                category?.name ?: "",
                style = text14White,
                color = Color.Black
            )
        }
    }
}