package kr.groo.android.weatherview.model

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

data class WeatherFalling(
    val fallingItemCount: Int,
    val fallingItemSpeed: Pair<Float, Float>,
    val fallingItemAngle: Pair<Float, Float>,
    val fallingItemRotate: Pair<Float, Float>,
    val fallingItemColor: Int?,
    val fallingItemImage: Drawable?,
    val fallingItemFromTheSky: Boolean
) {

    class Builder {
        private var fallingItemCount: Int = 30
        private var fallingItemSpeed: Pair<Float, Float> = Pair(1F, 5F)
        private var fallingItemAngle: Pair<Float, Float> = Pair(1F, 5F)
        private var fallingItemRotate: Pair<Float, Float> = Pair(0F, 0F)
        @ColorInt private var fallingItemColor: Int? = null
        private var fallingItemImage: Drawable? = null
        private var fallingItemFromTheSky: Boolean = false

        fun fallingItemCount(fallingItemCount: Int) = apply {
            this.fallingItemCount = fallingItemCount
        }

        fun fallingItemSpeed(fallingItemSpeed: Pair<Float, Float>) = apply {
            this.fallingItemSpeed = fallingItemSpeed
        }

        fun fallingItemAngle(fallingItemAngle: Pair<Float, Float>) = apply {
            this.fallingItemAngle = fallingItemAngle
        }

        fun fallingItemRotate(fallingItemRotate: Pair<Float, Float>) = apply {
            this.fallingItemRotate = fallingItemRotate
        }

        fun fallingItemColor(@ColorInt fallingItemColor: Int) = apply {
            this.fallingItemColor = fallingItemColor
        }

        fun fallingItemImage(fallingItemImage: Drawable) = apply {
            this.fallingItemImage = fallingItemImage
        }

        fun fallingItemFromTheSky(fallingItemFromTheSky: Boolean) = apply {
            this.fallingItemFromTheSky = fallingItemFromTheSky
        }

        fun build() = WeatherFalling(
            fallingItemCount,
            fallingItemSpeed,
            fallingItemAngle,
            fallingItemRotate,
            fallingItemColor,
            fallingItemImage,
            fallingItemFromTheSky
        )
    }
}
