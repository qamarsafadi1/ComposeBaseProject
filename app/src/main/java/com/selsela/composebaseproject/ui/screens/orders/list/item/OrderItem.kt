package com.selsela.composebaseproject.ui.screens.orders.list.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.data.remote.orders.model.Order
import com.selsela.composebaseproject.ui.theme.text14White


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderItem(order: Order) {
    Card(
        modifier = Modifier
            .padding(bottom = 11.dp, top = 11.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 191.dp)
            .clickable {
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        )
    ) {
        ListItem {
            Text(
                order.number,
                style = text14White,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderItemPreview() {
    OrderItem(order = Order(number = "test #100"))
}