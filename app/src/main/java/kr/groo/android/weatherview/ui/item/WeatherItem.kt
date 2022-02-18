package kr.groo.android.weatherview.ui.item

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PointF
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.ui.drawing.WeatherDrawing

interface WeatherItem {
    val screenWidth: Int
    val screenHeight: Int
    val itemSpeed: Float
    val itemAngle: Double
    var itemRotate: Float
    var itemRotateSize: Float
    val itemPoint: PointF
    val itemPaint: Paint
    val itemImage: Bitmap?
    var weatherFalling: WeatherFalling
    val drawing: WeatherDrawing
}
