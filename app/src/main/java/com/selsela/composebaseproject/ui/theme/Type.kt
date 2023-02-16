package com.selsela.composebaseproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val textStyle = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    color = Color.Black
)
val buttonText = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
    color = Color.White
)

val text14White = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    color = Color.White,
    lineHeight = 0.sp,
    letterSpacing = 0.sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both
    ),
    textAlign = TextAlign.Start
)
val text12 = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.5.sp,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both
    )
)