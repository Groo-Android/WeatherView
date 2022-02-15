package kr.groo.android.weatherview.domain

import android.graphics.Canvas
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherParam

interface WeatherItem {

    fun createItems(
        screenWidth: Int,
        screenHeight: Int,
        weatherKind: WeatherKind,
        weatherParam: WeatherParam
    ): Array<WeatherItem>

    fun drawItem(canvas: Canvas)
    fun moveItem()
    fun isItemShow(): Boolean
    fun resetItem()
}
