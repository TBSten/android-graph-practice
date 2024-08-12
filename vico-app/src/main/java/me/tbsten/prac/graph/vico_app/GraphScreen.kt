package me.tbsten.prac.graph.vico_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.data.rememberExtraLambda
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianLayerModel
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tbsten.prac.graph.vico_app.ui.theme.GraphPracTheme
import kotlin.random.Random

@Composable
fun GraphScreen() {
    val marker = rememberMarker()
    val chart = rememberCartesianChart(
        rememberLineCartesianLayer(
            LineCartesianLayer.LineProvider.series(
                rememberLine(remember { LineCartesianLayer.LineFill.single(fill(Color(0xffa485e0))) })
            )
        ),
//        startAxis = rememberStartAxis(),
//        bottomAxis = rememberBottomAxis(guideline = null),
//        marker = marker,
//        persistentMarkers = rememberExtraLambda(marker) { marker at 7f },
    )
    val modelProducer = remember {
        CartesianChartModelProducer()
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.runTransaction {
                lineSeries {
                    val x = (1..50).toList()
                    series(x, x.map { Random.nextFloat() * 15 })
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CartesianChartHost(
            chart = chart,
            modelProducer = modelProducer,
            modifier = Modifier
    //            .background(Color.Red)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun GraphScreenPreview() {
    GraphPracTheme {
        GraphScreen()
    }
}