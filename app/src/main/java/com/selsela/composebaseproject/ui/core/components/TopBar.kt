package com.selsela.composebaseproject.ui.core.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.navigation.Navigation
import com.selsela.composebaseproject.navigation.Screens


/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    currentScreen: Screens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (Navigation.showingTopBar(currentRoute = currentScreen)) {
        CenterAlignedTopAppBar(
            title = {
                Text(stringResource(id = currentScreen.title!!))
            },
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "back button",
                            modifier = Modifier.rotate(180f)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )
    }
}
