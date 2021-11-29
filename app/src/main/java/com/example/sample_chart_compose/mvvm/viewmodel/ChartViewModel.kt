package com.example.sample_chart_compose.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_chart_compose.mvvm.model.ChartModel
import com.example.sample_chart_compose.mvvm.uidata.ChartState
import com.example.sample_chart_compose.mvvm.uidata.ChartTemplateViewState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChartViewModel(
    private val model: ChartModel = ChartModel()
): ViewModel() {
    // チャートの表示データ数
    private val CHART_DISPLAYING_VALUE_NUM: Int = 30

    // データストリーム
    private val dataStream = MutableStateFlow<List<Int>>(emptyList())

    val viewState = combine(dataStream) {
        ChartTemplateViewState(
            ChartState(
                dataStream = dataStream.value,
                minValue = model.DATA_RANGE.first,
                maxValue = model.DATA_RANGE.last,
                displayingDataNum = CHART_DISPLAYING_VALUE_NUM
            )
        )
    }

    init {
        viewModelScope.launch {
            model.getDataStream().collect { data ->
                dataStream.update { stream ->
                    stream + data
                }
            }
        }
    }
}