package kr.groo.android.weatherview.ui.item

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PointF
import androidx.core.graphics.drawable.toBitmap
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.ui.drawing.RainDrawing

class RainItem(
    override val screenWidth: Int = 0,
    override val screenHeight: Int = 0,
    val itemWidth: Float = 0F,
    val itemHeight: Float = 0F,
    override val itemSpeed: Float = 0F,
    override val itemAngle: Double = 0.0,
    override var itemRotate: Float = 0F,
    override var itemRotateSize: Float = 0F,
    override val itemPoint: PointF = PointF(),
    override var weatherFalling: WeatherFalling? = null
) : WeatherItem {

    override val itemPaint = Paint().apply {
        weatherFalling?.fallingItemColor?.let { color = it }
        style = Paint.Style.FILL
        strokeWidth = itemWidth
    }

    override val itemImage: Bitmap? =
        weatherFalling?.fallingItemImage?.toBitmap(itemWidth.toInt(), itemHeight.toInt())

    override val drawing = RainDrawing(this)
}
