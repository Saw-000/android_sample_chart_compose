package com.example.sample_chart_compose.ui.compose.charts

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.sample_chart_compose.mvvm.uidata.ChartState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    state: ChartState
) = BarChart(
    modifier = modifier,
    dataStream = state.dataStream,
    minValue = state.minValue,
    maxValue = state.maxValue,
    displayingDataNum = state.displayingDataNum
)

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    dataStream: List<Int>,
    minValue: Int,
    maxValue: Int,
    displayingDataNum: Int,
) {
    val valueMaxHeight = (maxValue - minValue).toFloat()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column(modifier = Modifier.fillMaxWidth(0.8f)) {
            Box(modifier = Modifier.weight(1.5f)) {
                
            }
            var centerBoxSize by remember { mutableStateOf(IntSize.Zero) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .border(1.dp, Color.DarkGray)
                    .onSizeChanged { centerBoxSize = it }
            ) {

                val scrollState = rememberScrollState()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .horizontalScroll(scrollState),
                    contentAlignment = Alignment.BottomEnd
                ){
                    if (scrollState.value == scrollState.maxValue) {// ?????????????????????
                        CoroutineScope(Dispatchers.Main).launch {
                            scrollState.scrollTo(scrollState.maxValue)
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End
                    ) {

                        dataStream.forEach {
                            val barHeight = it.toFloat() / valueMaxHeight
                            val barWidth = 10f // centerBoxSize.toSize().width / displayingDataNum.toFloat()
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(barWidth.dp),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Canvas(
                                    modifier = Modifier
                                        .fillMaxHeight(barHeight)
                                        .fillMaxWidth()
                                ) {
                                    drawRect(
                                        color = Color.Blue.copy(alpha = 0.25f),
                                        topLeft = Offset(size.width / 4f, 0f),
                                        size = Size(size.width / 2f, size.height)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1.5f)) {

            }
        }
    }
}