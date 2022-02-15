package kr.groo.android.weatherview.model

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable

data class WeatherParam(
    val fallingItemCount: Int = 30,
    val fallingItemMinSpeed: Float = 1F,
    val fallingItemMaxSpeed: Float = 5F,
    val fallingItemMinAngle: Float = 1F,
    val fallingItemMaxAngle: Float = 5F,
    val fallingItemColor: Int = Color.WHITE,
    val fallingItemImage: Drawable? = null,
    val fallingItemStartBeforeLoad: Boolean = false,
    val snowPaintKind: SnowPaintKind = SnowPaintKind.General,
    val rainPaintKind: RainPaintKind = RainPaintKind.General
) {

    sealed class SnowPaintKind {
        abstract fun getPaint(weatherParam: WeatherParam): Paint

        object General : SnowPaintKind() {
            override fun getPaint(weatherParam: WeatherParam): Paint =
                Paint().apply {
                    color = weatherParam.fallingItemColor
                    style = Paint.Style.FILL
                }
        }
    }

    sealed class RainPaintKind {
        abstract fun getPaint(strokeWidth: Float, weatherParam: WeatherParam): Paint

        object General : RainPaintKind() {
            override fun getPaint(width: Float, weatherParam: WeatherParam): Paint =
                Paint().apply {
                    color = weatherParam.fallingItemColor
                    style = Paint.Style.FILL
                    strokeWidth = width
                }
        }
    }
}
