package com.selsela.composebaseproject.ui.core.components.language.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.ui.theme.BorderColor
import com.selsela.composebaseproject.ui.theme.TextFieldBg
import com.selsela.composebaseproject.ui.theme.text14White


@Composable
 fun LanguageItem(selectedItem: Int, onCheck: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding( top = 15.dp)
            .fillMaxWidth()
    ) {
        repeat(2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
                    .background(TextFieldBg, RoundedCornerShape(11.dp))
                    .clip(RoundedCornerShape(11.dp))
                    .clickable(
                    ) {
                        onCheck(it)
                    }
                    .border(
                        width = 1.dp,
                        color = BorderColor,
                        RoundedCornerShape(11.dp)
                    )
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = if (it == 0) "العربية" else "English",
                    style = text14White,
                    color = Color.White
                )

                Image(
                    painter =
                    if (it == selectedItem)
                        painterResource(id = R.drawable.checked)
                    else painterResource(id = R.drawable.uncheckedrb),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}
