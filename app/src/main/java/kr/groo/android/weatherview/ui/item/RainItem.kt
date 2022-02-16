package kr.groo.android.weatherview.ui.item

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PointF
import androidx.core.graphics.drawable.toBitmap
import kr.groo.android.weatherview.domain.WeatherItem
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherParam
import kr.groo.android.weatherview.util.RandomUtil.getRandomFromUntil
import kr.groo.android.weatherview.util.RandomUtil.getRandomSign
import kr.groo.android.weatherview.util.RandomUtil.getRandomUntil
import kotlin.math.cos
import kotlin.math.sin

class RainItem(
    private val screenWidth: Int = 0,
    private val screenHeight: Int = 0,
    private val itemWidth: Float = 0F,
    private val itemHeight: Float = 0F,
    private val itemSpeed: Float = 0F,
    private val itemAngle: Double = 0.0,
    private var itemRotate: Float = 0F,
    private var itemRotateSize: Float = 0F,
    private val itemPoint: PointF = PointF(),
    private val itemPaint: Paint = Paint(),
    private var itemImage: Bitmap? = null,
    private var weatherParam: WeatherParam? = null
) : WeatherItem {

    override fun createItems(
        screenWidth: Int,
        screenHeight: Int,
        weatherKind: WeatherKind,
        weatherParam: WeatherParam
    ): Array<WeatherItem> {

        return Array(weatherParam.fallingItemCount) {
            with(weatherParam) {
                var strokeWidth = 0F
                var strokeHeight = 0F

                if (weatherKind is WeatherKind.Rain) {
                    strokeWidth = getRandomFromUntil(weatherKind.minWidth, weatherKind.maxWidth)
                    strokeHeight = getRandomFromUntil(weatherKind.minHeight, weatherKind.maxHeight)
                }

                RainItem(
                    screenWidth = screenWidth,
                    screenHeight = screenHeight,
                    itemWidth = strokeWidth,
                    itemHeight = strokeHeight,
                    itemSpeed = getRandomFromUntil(fallingItemSpeed.first, fallingItemSpeed.second),
                    itemAngle = Math.toRadians((getRandomFromUntil(fallingItemAngle.first, fallingItemAngle.second) * getRandomSign()).toDouble()),
                    itemRotateSize = getRandomFromUntil(fallingItemRotate.first, fallingItemRotate.second),
                    itemPoint = PointF(getRandomUntil(screenWidth.toFloat()), if (fallingItemFromTheSky) (-screenHeight / 2).toFloat() else 0F),
                    itemPaint = rainPaintKind.getPaint(strokeWidth, this),
                    itemImage = fallingItemImage?.toBitmap(strokeWidth.toInt(), strokeHeight.toInt()),
                    weatherParam = this
                )
            }
        }
    }

    override fun drawItem(canvas: Canvas) {
        moveItem()

        if (itemImage != null) {
            Matrix().apply {
                postRotate(itemRotate, itemWidth / 2, itemHeight / 2)
                postTranslate(itemPoint.x, itemPoint.y)
                canvas.drawBitmap(itemImage!!, this, itemPaint)
            }
        } else {
            canvas.drawLine(itemPoint.x, itemPoint.y, itemPoint.x, itemPoint.y + itemHeight, itemPaint)
        }
    }

    override fun moveItem() {
        itemPoint.apply {
            x = x.plus(itemSpeed * sin(itemAngle)).toFloat()
            y = y.plus(itemSpeed * cos(itemAngle)).toFloat()
        }

        if (isItemShow().not()) {
            resetItem()
        }

        itemRotate += itemRotateSize
    }

    override fun isItemShow(): Boolean {
        return (itemPoint.x + itemWidth <= screenWidth) && (itemPoint.y + itemHeight <= screenHeight)
    }

    override fun resetItem() {
        itemPoint.apply {
            x = getRandomUntil(screenWidth.toFloat())
            y = if (weatherParam?.fallingItemFromTheSky == true) (-screenHeight / 2).toFloat() else 0F
        }
    }
}
