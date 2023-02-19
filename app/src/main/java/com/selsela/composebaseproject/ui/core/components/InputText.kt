package com.selsela.composebaseproject.ui.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.selsela.composebaseproject.ui.theme.BorderColor
import com.selsela.composebaseproject.ui.theme.Red
import com.selsela.composebaseproject.ui.theme.SecondaryColor
import com.selsela.composebaseproject.ui.theme.TextFieldBg
import com.selsela.composebaseproject.ui.theme.text12
import com.selsela.composebaseproject.ui.theme.text14White


@Composable
fun InputText(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    inputType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    cursorColor: Color = Color.White,
    cornerRaduis: Dp = 8.dp,
    fillMax: Float = 1f,
    borderColor: Color = BorderColor,
    isValid: Boolean = true,
    validationMessage: String = "",
    textStyle: TextStyle = text14White
) {
    val color: Color by animateColorAsState(
        borderColor
    )
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(fillMax)
                    .requiredHeight(48.dp)
                    .background(TextFieldBg, shape = RoundedCornerShape(cornerRaduis))
                    .border(1.dp, color = color, RoundedCornerShape(cornerRaduis))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = SecondaryColor,
                        style = textStyle
                    )
                }
                innerTextField()

            }
        },
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = inputType,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        cursorBrush = SolidColor(cursorColor),
        visualTransformation = if (inputType != KeyboardType.Password)
            VisualTransformation.None
        else PasswordVisualTransformation()

    )

    AnimatedVisibility(visible = isValid.not()) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = validationMessage,
                style = text12,
                color = Red,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

