package kr.groo.android.weatherview.domain

import android.graphics.Canvas
import kr.groo.android.weatherview.model.WeatherParam

interface WeatherFunction {
    fun createItem(weatherParam: WeatherParam): Array<WeatherFunction>
    fun drawItem(canvas: Canvas)
}