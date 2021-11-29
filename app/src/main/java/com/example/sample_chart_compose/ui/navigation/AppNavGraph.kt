package com.example.sample_chart_compose.ui.navigation

import androidx.navigation.*

fun NavGraphBuilder.appNavGraph(
    navigateToDanceRecord: (Long, NavBackStackEntry) -> Unit,
) {
    navigation(
        route = PageNav.HOME_TAB_ROOT_ROUTE,
        startDestination = PageNav.Chart.route
    ) {
        homeTabNavGraph(
            navigateToDanceRecord = navigateToDanceRecord
        )
    }
}