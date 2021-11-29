package com.example.sample_chart_compose.mvvm.uidata

data class ChartTemplateViewState(
    val chartState: ChartState
){
    companion object {
        val EMPTY = ChartTemplateViewState(
            ChartState(
                dataStream = emptyList(),
                minValue = 0,
                maxValue = 0,
                displayingDataNum = 0
            )
        )
    }
}