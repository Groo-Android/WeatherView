package kr.groo.android.weatherview.model

sealed class WeatherKind {

    data class Snow(
        val minSize: Float = 5F,
        val maxSize: Float = 10F
    ) : WeatherKind()

    data class Rain(
        val minWidth: Float = 1F,
        val maxWidth: Float = 3F,
        val minHeight: Float = 40F,
        val maxHeight: Float = 60F
    ) : WeatherKind()
}
