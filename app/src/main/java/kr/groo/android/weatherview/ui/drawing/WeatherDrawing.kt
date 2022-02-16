package kr.groo.android.weatherview.ui.drawing

import android.graphics.Canvas
import kr.groo.android.weatherview.ui.item.WeatherItem

interface WeatherDrawing {
    val weatherItem: WeatherItem

    fun draw(canvas: Canvas)
    fun move()
    fun show(): Boolean
    fun reset()
}
