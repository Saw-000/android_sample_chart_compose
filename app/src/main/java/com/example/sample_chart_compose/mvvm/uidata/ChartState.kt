package com.example.sample_chart_compose.mvvm.uidata

data class ChartState(
    val dataStream: List<Int>,
    val minValue: Int,
    val maxValue: Int,
    val displayingDataNum: Int
)