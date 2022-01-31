package kr.groo.android.weatherview.model

sealed class WeatherKind {

    data class Snow(
        val minSize: Int,
        val maxSize: Int
    ): WeatherKind()

    data class Rain(
        val minWidth: Int,
        val maxWidth: Int,
        val minHeight: Int,
        val maxHeight: Int
    ): WeatherKind()
}