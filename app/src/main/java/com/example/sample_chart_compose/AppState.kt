package com.example.sample_chart_compose

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sample_chart_compose.ui.manager.IndicatorManager
import com.example.sample_chart_compose.ui.navigation.PageNav
import com.example.sample_chart_compose.ui.manager.SnackBarManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@Stable
class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackBarManager: SnackBarManager,
    private val indicatorManager: IndicatorManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    // Process snackBars coming from SnackBarManager
    init {
        coroutineScope.launch {
            launch {
                snackBarManager.messages.collect { currentMessages ->
                    if (currentMessages.isNotEmpty()) {
                        val message = currentMessages[0]
                        val text = resources.getText(message.messageId)
                        scaffoldState.snackbarHostState.showSnackbar(text.toString())
                        snackBarManager.setMessageShown(message.id)
                    }
                }
            }

            launch {
                indicatorManager.indicators.collect {
                    _showIndicator.value = it.isNotEmpty()
                }
            }
        }
    }

    private val _showIndicator = MutableStateFlow(false)
    val showIndicator = _showIndicator.asStateFlow()

    // list of Bottom Tab items
    val bottomBarTabs = listOf<PageNav>(
        PageNav.Chart,
        PageNav.SomeOther
    )

    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    // show bottom bar flag
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    // current navigation route
    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    }

    fun navigateToDanceRecord(recordId: Long, from: NavBackStackEntry) {

    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackBarManager: SnackBarManager = SnackBarManager,
    indicatorManager: IndicatorManager = IndicatorManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackBarManager, indicatorManager, resources, coroutineScope) {
        AppState(scaffoldState, navController, snackBarManager, indicatorManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED