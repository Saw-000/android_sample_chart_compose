package com.example.sample_chart_compose.ui.compose.chart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sample_chart_compose.mvvm.uidata.ChartTemplateViewState

@Composable
fun ChartTemplate(
    viewState: ChartTemplateViewState
) {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "dataStream: ${viewState.chartState.dataStream}"
    )
}