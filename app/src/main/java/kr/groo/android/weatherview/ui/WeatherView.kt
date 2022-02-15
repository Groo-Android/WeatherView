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
import kr.groo.android.weatherview.domain.WeatherItem
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherParam

class WeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private val weatherScope = CoroutineScope(Dispatchers.IO)
    }

    private var previousTime = System.currentTimeMillis()
    private var weatherKind: WeatherKind? = null
    private var weatherParam: WeatherParam? = null
    private var weatherItems: Array<WeatherItem>? = null
    private var weatherJob: Job? = null

    init {
        visibility = GONE
    }

    fun setWeatherData(weatherKind: WeatherKind, weatherParam: WeatherParam) {
        this.weatherKind = weatherKind
        this.weatherParam = weatherParam
        visibility = VISIBLE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        weatherItems = weatherKind?.createItems(w, h, weatherParam!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val currentTime = System.currentTimeMillis()
        Log.e("Groo", "FPS: ${currentTime - previousTime}")
        previousTime = currentTime

        weatherItems?.forEach { it.drawItem(canvas) }
        weatherJob = weatherScope.launch {
            delay(5)
            postInvalidate()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        weatherJob?.cancel()
    }
}
