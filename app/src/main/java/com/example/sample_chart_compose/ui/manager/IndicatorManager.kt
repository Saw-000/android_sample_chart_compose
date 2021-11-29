package com.example.sample_chart_compose.ui.manager

import kotlinx.coroutines.flow.*
import java.util.*

data class IndicatorTag(val id: Long = UUID.randomUUID().mostSignificantBits)

object IndicatorManager {
    private val _indicators: MutableStateFlow<List<IndicatorTag>> = MutableStateFlow(emptyList())
    val indicators: StateFlow<List<IndicatorTag>>  = _indicators.asStateFlow()

    fun show(): Long {
        val i = IndicatorTag()
        _indicators.update { it + i }
        return i.id
    }

    fun hide(id: Long) {
        _indicators.update { list ->  list.filterNot { it.id == id } }
    }

    fun reset() {
        _indicators.value = emptyList()
    }
}