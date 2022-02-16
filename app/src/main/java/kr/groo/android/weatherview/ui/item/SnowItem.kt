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

class SnowItem(
    private val screenWidth: Int = 0,
    private val screenHeight: Int = 0,
    private val itemSize: Float = 0F,
    private val itemSpeed: Float = 0F,
    private val itemAngle: Double = 0.0,
    private var itemRotate: Float = 0F,
    private var itemRotateSize: Float = 0F,
    private val itemPoint: PointF = PointF(),
    private var itemPaint: Paint = Paint(),
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
                val size = if (weatherKind is WeatherKind.Snow) getRandomFromUntil(weatherKind.minSize, weatherKind.maxSize) else 0F
                SnowItem(
                    screenWidth = screenWidth,
                    screenHeight = screenHeight,
                    itemSize = size,
                    itemSpeed = getRandomFromUntil(fallingItemSpeed.first, fallingItemSpeed.second),
                    itemAngle = Math.toRadians((getRandomFromUntil(fallingItemAngle.first, fallingItemAngle.second) * getRandomSign()).toDouble()),
                    itemRotateSize = getRandomFromUntil(fallingItemRotate.first, fallingItemRotate.second),
                    itemPoint = PointF(getRandomUntil(screenWidth.toFloat()), if (fallingItemFromTheSky) (-screenHeight / 2).toFloat() else 0F),
                    itemPaint = snowPaintKind.getPaint(weatherParam),
                    itemImage = fallingItemImage?.toBitmap(size.toInt(), size.toInt()),
                    weatherParam = this
                )
            }
        }
    }

    override fun drawItem(canvas: Canvas) {
        moveItem()

        if (itemImage != null) {
            Matrix().apply {
                postRotate(itemRotate, itemSize / 2, itemSize / 2)
                postTranslate(itemPoint.x, itemPoint.y)
                canvas.drawBitmap(itemImage!!, this, itemPaint)
            }
        } else {
            canvas.drawCircle(itemPoint.x, itemPoint.y, itemSize, itemPaint)
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
        return (itemPoint.x + itemSize <= screenWidth) && (itemPoint.y + itemSize <= screenHeight)
    }

    override fun resetItem() {
        itemPoint.apply {
            x = getRandomUntil(screenWidth.toFloat())
            y = if (weatherParam?.fallingItemFromTheSky == true) (-screenHeight / 2).toFloat() else 0F
        }
    }
}
