package com.selsela.composebaseproject

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.selsela.composebaseproject.navigation.BottomSheetScreen
import com.selsela.composebaseproject.navigation.NavigationHost
import com.selsela.composebaseproject.navigation.Screens
import com.selsela.composebaseproject.ui.core.SheetLayout
import com.selsela.composebaseproject.ui.core.components.TopBar
import com.selsela.composebaseproject.ui.theme.BottomSheetShape
import com.selsela.composebaseproject.ui.theme.ComposeBaseProjectTheme
import com.selsela.composebaseproject.util.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBaseProjectTheme {
                App()
            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun App(modifier: Modifier = Modifier) {

        val scope = rememberCoroutineScope()
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = Screens.valueOf(
            backStackEntry?.destination?.route?.substringBefore("/") ?: Screens.Splash.name
        )
        var currentBottomSheet: BottomSheetScreen? by remember {
            mutableStateOf(null)
        }

        val scaffoldState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
            skipHalfExpanded = true
        )

        val closeSheet: () -> Unit = {
            scope.launch {
                scaffoldState.hide()
            }
        }
        val openSheet: (BottomSheetScreen) -> Unit = {
            currentBottomSheet = it
            scope.launch { scaffoldState.animateTo(ModalBottomSheetValue.Expanded) }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                Scaffold(topBar = {
                    TopBar(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = {
                            navController.navigateUp()
                        }
                    )
                }) {
                    NavigationHost(
                        modifier = modifier.padding(it),
                        navController = navController,
                        openSheet
                    )
                }
                ModalBottomSheetLayout(
                    sheetState = scaffoldState,
                    sheetBackgroundColor = Color.White,
                    sheetShape = BottomSheetShape,
                    sheetContent = {
                        Spacer(modifier = Modifier.height(1.dp))
                        if (currentBottomSheet != null)
                            SheetLayout(currentBottomSheet!!, closeSheet)
                    },
                    sheetElevation = 10.dp
                ) {}
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ComposeBaseProjectTheme {
        }
    }
}