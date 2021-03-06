package kr.groo.android.weatherview.ui.drawing

import android.graphics.Canvas
import android.graphics.Matrix
import kr.groo.android.weatherview.ui.item.RainItem
import kr.groo.android.weatherview.util.RandomUtil
import kotlin.math.cos
import kotlin.math.sin

class RainDrawing(override val weatherItem: RainItem) : WeatherDrawing {

    override fun draw(canvas: Canvas) {
        move()

        if (weatherItem.itemImage != null) {
            Matrix().apply {
                postRotate(weatherItem.itemRotate, weatherItem.itemWidth / 2, weatherItem.itemHeight / 2)
                postTranslate(weatherItem.itemPoint.x, weatherItem.itemPoint.y)
                canvas.drawBitmap(weatherItem.itemImage, this, weatherItem.itemPaint)
            }
        } else {
            canvas.drawLine(weatherItem.itemPoint.x, weatherItem.itemPoint.y, weatherItem.itemPoint.x, weatherItem.itemPoint.y + weatherItem.itemHeight, weatherItem.itemPaint)
        }
    }

    override fun move() {
        weatherItem.itemPoint.apply {
            x = x.plus(weatherItem.itemSpeed * sin(weatherItem.itemAngle)).toFloat()
            y = y.plus(weatherItem.itemSpeed * cos(weatherItem.itemAngle)).toFloat()
        }

        if (show().not()) {
            reset()
        }

        if (weatherItem.itemRotateSize != 0F) {
            weatherItem.itemRotate += weatherItem.itemRotateSize
        }
    }

    override fun show(): Boolean =
        (weatherItem.itemPoint.x + weatherItem.itemWidth <= weatherItem.screenWidth) &&
                (weatherItem.itemPoint.y + weatherItem.itemHeight <= weatherItem.screenHeight)

    override fun reset() {
        weatherItem.itemPoint.apply {
            x = RandomUtil.getRandomUntil(weatherItem.screenWidth.toFloat())
            y = if (weatherItem.weatherFalling.fallingItemFromTheSky) (-weatherItem.screenHeight / 2).toFloat() else 0F
        }
    }
}
