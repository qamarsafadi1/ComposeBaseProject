package com.selsela.composebaseproject.ui.screens.categories.list.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.data.remote.categories.model.Service
import com.selsela.composebaseproject.ui.core.components.AsyncImage
import com.selsela.composebaseproject.ui.theme.text14White
import com.selsela.composebaseproject.util.log
import com.selsela.composebaseproject.util.toJson

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItem(category: Service, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable {
            onClick()
        },
        trailing = {
            AsyncImage(
                imageUrl = category.imageUtl,
                modifier = Modifier.size(60.dp)
            )
        }
    ) {
        Text(
            category.name,
            style = text14White,
            color = Color.Black
        )
    }

}