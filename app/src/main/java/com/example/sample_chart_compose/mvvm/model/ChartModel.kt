package com.example.sample_chart_compose.mvvm.model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class ChartModel(
    val DATA_RANGE: IntRange = 0..100,// データ値範囲
    val DATA_STREAM_INTERVAL_MILLIS: Long = 500L// データ取得インターバル
) {

    // 一定時間毎にデータ取得
    fun getDataStream() = flow<Int> {
        while (true) {
            emit(getData())
            delay(DATA_STREAM_INTERVAL_MILLIS)
        }
    }

    // データ取得
    private fun getData(): Int
        = DATA_RANGE.random()
}