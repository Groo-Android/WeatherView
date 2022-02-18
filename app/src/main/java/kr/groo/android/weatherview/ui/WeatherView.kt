package kr.groo.android.weatherview.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.groo.android.weatherview.domain.GetWeatherItemsUseCase
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.ui.item.WeatherItem

class WeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private val weatherScope = CoroutineScope(Dispatchers.IO)
    }

    private var previousTime = System.currentTimeMillis()
    private var weatherJob: Job? = null
    private var weatherItems: Array<WeatherItem>? = null

    var weatherFalling: WeatherFalling? = null
    var weatherKind: WeatherKind? = null
    var getWeatherItemsUseCase: GetWeatherItemsUseCase? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        weatherItems = getWeatherItemsUseCase?.invoke(w, h, weatherKind, weatherFalling)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        printLog()

        weatherItems?.forEach { it.drawing.draw(canvas) }
        weatherJob = weatherScope.launch {
            delay(5)
            postInvalidate()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        weatherJob?.cancel()
    }

    private fun printLog() {
        val currentTime = System.currentTimeMillis()
        Log.e("Groo", "FPS: ${currentTime - previousTime}")
        previousTime = currentTime
    }
}
