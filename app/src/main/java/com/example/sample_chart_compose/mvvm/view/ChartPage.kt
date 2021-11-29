package com.example.sample_chart_compose.mvvm.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.sample_chart_compose.mvvm.uidata.ChartTemplateViewState
import com.example.sample_chart_compose.mvvm.viewmodel.ChartViewModel
import com.example.sample_chart_compose.ui.compose.chart.ChartTemplate

@Composable
fun ChartPage(
    viewModel: ChartViewModel
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val viewState by remember (viewModel.viewState, lifecycleOwner) {
        viewModel.viewState.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED,
        )
    }.collectAsState(ChartTemplateViewState.EMPTY)

    ChartTemplate(
        viewState = viewState
    )
}