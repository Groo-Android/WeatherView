package kr.groo.android.weatherview.ui.item

import android.graphics.Bitmap
import android.graphics.Canvas
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
    private val itemPoint: PointF = PointF(),
    private var itemPaint: Paint = Paint(),
    private var itemImage: Bitmap? = null
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
                    itemSpeed = getRandomFromUntil(fallingItemMinSpeed, fallingItemMaxSpeed),
                    itemAngle = Math.toRadians((getRandomFromUntil(fallingItemMinAngle, fallingItemMaxAngle) * getRandomSign()).toDouble()),
                    itemPoint = PointF(getRandomUntil(screenWidth.toFloat()), if (fallingItemStartBeforeLoad.not()) (-screenHeight / 2).toFloat() else getRandomUntil(screenHeight.toFloat())),
                    itemPaint = snowPaintKind.getPaint(weatherParam),
                    itemImage = fallingItemImage?.toBitmap(size.toInt(), size.toInt())
                )
            }
        }
    }

    override fun drawItem(canvas: Canvas) {
        moveItem()

        if (itemImage != null) {
            canvas.drawBitmap(itemImage!!, itemPoint.x, itemPoint.y, itemPaint)
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
    }

    override fun isItemShow(): Boolean {
        return (itemPoint.x + itemSize <= screenWidth) && (itemPoint.y + itemSize <= screenHeight)
    }

    override fun resetItem() {
        itemPoint.apply {
            x = getRandomUntil(screenWidth.toFloat())
            y = (-screenHeight / 2).toFloat()
        }
    }
}
