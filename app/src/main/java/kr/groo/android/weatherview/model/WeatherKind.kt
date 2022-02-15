package kr.groo.android.weatherview.model

import kr.groo.android.weatherview.domain.WeatherItem
import kr.groo.android.weatherview.ui.item.RainItem
import kr.groo.android.weatherview.ui.item.SnowItem

sealed class WeatherKind {

    abstract fun createItems(
        screenWidth: Int,
        screenHeight: Int,
        weatherParam: WeatherParam
    ): Array<WeatherItem>

    data class Snow(
        val minSize: Float = 5F,
        val maxSize: Float = 10F
    ) : WeatherKind() {

        override fun createItems(
            screenWidth: Int,
            screenHeight: Int,
            weatherParam: WeatherParam
        ): Array<WeatherItem> =
            SnowItem().createItems(screenWidth, screenHeight, this, weatherParam)
    }

    data class Rain(
        val minWidth: Float = 1F,
        val maxWidth: Float = 3F,
        val minHeight: Float = 40F,
        val maxHeight: Float = 60F
    ) : WeatherKind() {

        override fun createItems(
            screenWidth: Int,
            screenHeight: Int,
            weatherParam: WeatherParam
        ): Array<WeatherItem> =
            RainItem().createItems(screenWidth, screenHeight, this, weatherParam)
    }
}
