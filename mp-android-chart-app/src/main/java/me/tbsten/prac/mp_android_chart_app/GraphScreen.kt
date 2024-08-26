package me.tbsten.prac.mp_android_chart_app

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry

@Composable
fun GraphScreen() {
    Box(modifier =  Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(
            factory = { context ->
                RadarChart(context).apply {
                    val dataSet1 = RadarDataSet(
                        listOf(
                            RadarEntry(50f),
                            RadarEntry(30f),
                            RadarEntry(20f),
                            RadarEntry(60f),
                            RadarEntry(60f),
                            RadarEntry(40f),
                            RadarEntry(60f),
                        ),
                        "label1",
                    ).apply {
                        val set1 = this
                        set1.setColor(Color.rgb(103, 110, 129))
                        set1.setFillColor(Color.rgb(103, 110, 129))
                        set1.setDrawFilled(true)
                        set1.setFillAlpha(180)
                        set1.setLineWidth(2f)
                        set1.setDrawHighlightCircleEnabled(true)
                        set1.setDrawHighlightIndicators(false)
                    }
                    this.data = RadarData(
                        listOf(
                            dataSet1,
                        ),
                    )

                    this.isRotationEnabled = false

                    this.legend.isEnabled = false
                    this.description.isEnabled = false
                    this.invalidate()
                }
            },
            modifier = Modifier
                .border(4.dp, androidx.compose.ui.graphics.Color.Red)
                .fillMaxWidth()
                .aspectRatio(1f / 1f),
        )
    }
}

@Preview
@Composable
private fun GraphScreenPreview() {
    GraphScreen()
}