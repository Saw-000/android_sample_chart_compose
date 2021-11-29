package com.example.sample_chart_compose.ui.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sample_chart_compose.mvvm.view.ChartPage
import com.example.sample_chart_compose.mvvm.view.HelpPage
import com.example.sample_chart_compose.mvvm.viewmodel.ChartViewModel
import com.example.sample_chart_compose.mvvm.viewmodel.HelpViewModel

// home tab graph
fun NavGraphBuilder.homeTabNavGraph(
    navigateToDanceRecord: (Long, NavBackStackEntry) -> Unit
) {
    composable(PageNav.Chart.route) {
        val viewModel = remember { ChartViewModel() }
        ChartPage(viewModel = viewModel)
    }
    composable(PageNav.SomeOther.route) {
        val viewModel = remember { HelpViewModel() }
        HelpPage(viewModel = viewModel)
    }
}