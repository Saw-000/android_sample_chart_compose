package com.example.sample_chart_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.NavHost
import com.example.sample_chart_compose.ui.compose.HomeBottomBar
import com.example.sample_chart_compose.ui.compose.theme.Sample_chart_composeTheme
import com.example.sample_chart_compose.ui.navigation.PageNav
import com.example.sample_chart_compose.ui.navigation.appNavGraph
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun AppUi() {
    val appState = rememberAppState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val showIndicator by remember(appState.showIndicator, lifecycleOwner) {
        appState.showIndicator.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED,
        )
    }.collectAsState(false)

    // screen
    Sample_chart_composeTheme {
        Scaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    HomeBottomBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                }
            },
            scaffoldState = appState.scaffoldState,
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> Snackbar(snackbarData) }
                )
            }
        ){ paddingValue ->
            NavHost(
                navController = appState.navController,
                startDestination = PageNav.HOME_TAB_ROOT_ROUTE,
                modifier = Modifier.padding(paddingValue)
            ) {
                appNavGraph(
                    navigateToDanceRecord = appState::navigateToDanceRecord
                )
            }
        }

        // indicator
        if (showIndicator) {
            Surface(color = Color.Black.copy(alpha = 0.25f)) {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
        }
    }
}