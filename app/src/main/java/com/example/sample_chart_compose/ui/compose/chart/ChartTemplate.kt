package com.example.sample_chart_compose.ui.compose.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sample_chart_compose.mvvm.uidata.ChartTemplateViewState
import com.example.sample_chart_compose.ui.compose.charts.BarChart

@Composable
fun ChartTemplate(
    viewState: ChartTemplateViewState
) {
    BarChart(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(color = Color.Magenta.copy(alpha = 0.1f))
        ,
        state = viewState.chartState
    )
}