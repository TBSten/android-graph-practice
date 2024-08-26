package me.tbsten.prac.full_scratch_app

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.translationMatrix
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun GraphScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val values = listOf(
            50f,
            40f,
            10f,
            30f,
            40f,
            00f,
            50f,
        )
        MyRadarChart(
            values = values,
            minValue = 0f,
            maxValue = 50f,
            steps = 10f,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

/**
 * values の個数が奇数だと下に若干空間ができるため注意。
 */
@Composable
fun MyRadarChart(
    values :List<Float>,
    minValue: Float = values.min(),
    maxValue: Float = values.max(),
    steps: Float = (maxValue - minValue) / 10,
    valueStrokeColor: Color = Color.Red,
    valueStrokeWidth: Dp = 3.dp,
    webStrokeColor: Color = Color.Gray,
    webStrokeWidth: Dp = 1.dp,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
            .padding(20.dp)
            .aspectRatio(1f / 1f),
        onDraw = {
            // TODO web
            var currentWebValue = minValue
            while(currentWebValue <= maxValue) {
                drawValues(
                    values = values.map { currentWebValue },
                    strokeWidth = webStrokeWidth,
                    color = webStrokeColor,
                    minValue = minValue,
                    maxValue = maxValue,
                )
                currentWebValue += steps
            }
            // values
            drawValues(
                values = values,
                strokeWidth = valueStrokeWidth,
                color = valueStrokeColor,
                minValue = minValue,
                maxValue = maxValue,
            )
        },
    )
}

private fun DrawScope.drawValues(
    values: List<Float>,
    minValue: Float = values.min(),
    maxValue: Float = values.max(),
    strokeWidth: Dp = 1.dp,
    color: Color = Color.Black,
) {
    val radius = size.minDimension / 2
    val stepAngle = 360f / values.size
    val points = values.mapIndexed { index, value ->
        val scaledValue = value / maxValue
        val angle = Math.toRadians((stepAngle * index).toDouble() - 90)
        val x = center.x + scaledValue * radius * cos(angle).toFloat()
        val y = center.y + scaledValue * radius * sin(angle).toFloat()
        Offset(x, y)
    }

    drawPath(
        path = Path().apply {
            points.forEachIndexed {  index, point ->
                if(index == 0) {
                    moveTo(point.x, point.y)
                }else{
                    lineTo(point.x, point.y)
                }
            }
            close()
        },
        color = color,
        style = Stroke(width = strokeWidth.toPx()),
    )
}

@Preview(showBackground = true)
@Composable
private fun GraphScreenPreview() {
    GraphScreen()
}
