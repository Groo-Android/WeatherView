package kr.groo.android.weatherview.ui.drawing

import android.graphics.Canvas
import android.graphics.Matrix
import kr.groo.android.weatherview.ui.item.SnowItem
import kr.groo.android.weatherview.util.RandomUtil
import kotlin.math.cos
import kotlin.math.sin

class SnowDrawing(override val weatherItem: SnowItem) : WeatherDrawing {

    override fun draw(canvas: Canvas) {
        move()

        if (weatherItem.itemImage != null) {
            Matrix().apply {
                postRotate(weatherItem.itemRotate, weatherItem.itemSize / 2, weatherItem.itemSize / 2)
                postTranslate(weatherItem.itemPoint.x, weatherItem.itemPoint.y)
                canvas.drawBitmap(weatherItem.itemImage, this, weatherItem.itemPaint)
            }
        } else {
            canvas.drawCircle(weatherItem.itemPoint.x, weatherItem.itemPoint.y, weatherItem.itemSize, weatherItem.itemPaint)
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

        weatherItem.itemRotate += weatherItem.itemRotateSize
    }

    override fun show(): Boolean =
        (weatherItem.itemPoint.x + weatherItem.itemSize <= weatherItem.screenWidth)
                && (weatherItem.itemPoint.y + weatherItem.itemSize <= weatherItem.screenHeight)

    override fun reset() {
        weatherItem.itemPoint.apply {
            x = RandomUtil.getRandomUntil(weatherItem.screenWidth.toFloat())
            y = if (weatherItem.weatherFalling?.fallingItemFromTheSky == true) (-weatherItem.screenHeight / 2).toFloat() else 0F
        }
    }
}
