package kr.groo.android.weatherview.ui.item

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PointF
import androidx.core.graphics.drawable.toBitmap
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.ui.drawing.RainDrawing

class RainItem(
    val itemWidth: Float,
    val itemHeight: Float,
    override val screenWidth: Int,
    override val screenHeight: Int,
    override val itemSpeed: Float,
    override val itemAngle: Double,
    override var itemRotate: Float,
    override var itemRotateSize: Float,
    override val itemPoint: PointF,
    override var weatherFalling: WeatherFalling
) : WeatherItem {

    override val itemPaint = Paint().apply {
        weatherFalling.fallingItemColor?.let { color = it }
        style = Paint.Style.FILL
        strokeWidth = itemWidth
    }

    override val itemImage: Bitmap? =
        weatherFalling.fallingItemImage?.toBitmap(itemWidth.toInt(), itemHeight.toInt())

    override val drawing = RainDrawing(this)
}
