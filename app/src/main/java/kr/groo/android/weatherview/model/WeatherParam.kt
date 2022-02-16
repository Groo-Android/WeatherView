package kr.groo.android.weatherview.model

import android.graphics.Paint
import android.graphics.drawable.Drawable

data class WeatherParam(
    val fallingItemCount: Int = 30,
    val fallingItemSpeed: Pair<Float, Float> = Pair(1F, 5F),
    val fallingItemAngle: Pair<Float, Float> = Pair(1F, 5F),
    val fallingItemRotate: Pair<Float, Float> = Pair(0F, 0F),
    val fallingItemColor: Int? = null,
    val fallingItemImage: Drawable? = null,
    val fallingItemFromTheSky: Boolean = true,
    val snowPaintKind: SnowPaintKind = SnowPaintKind.General,
    val rainPaintKind: RainPaintKind = RainPaintKind.General
) {

    sealed class SnowPaintKind {
        abstract fun getPaint(weatherParam: WeatherParam): Paint

        object General : SnowPaintKind() {
            override fun getPaint(weatherParam: WeatherParam): Paint =
                Paint().apply {
                    weatherParam.fallingItemColor?.let { color = it }
                    style = Paint.Style.FILL
                }
        }
    }

    sealed class RainPaintKind {
        abstract fun getPaint(_strokeWidth: Float, weatherParam: WeatherParam): Paint

        object General : RainPaintKind() {
            override fun getPaint(_strokeWidth: Float, weatherParam: WeatherParam): Paint =
                Paint().apply {
                    weatherParam.fallingItemColor?.let { color = it }
                    style = Paint.Style.FILL
                    strokeWidth = _strokeWidth
                }
        }
    }
}
