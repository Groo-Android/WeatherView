package kr.groo.android.weatherview.ui.item

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PointF
import androidx.core.graphics.drawable.toBitmap
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.ui.drawing.SnowDrawing

class SnowItem(
    override val screenWidth: Int,
    override val screenHeight: Int = 0,
    val itemSize: Float = 0F,
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
    }

    override val itemImage: Bitmap? =
        weatherFalling?.fallingItemImage?.toBitmap(itemSize.toInt(), itemSize.toInt())

    override val drawing = SnowDrawing(this)
}
