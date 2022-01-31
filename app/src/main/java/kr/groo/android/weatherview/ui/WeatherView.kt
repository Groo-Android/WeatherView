package kr.groo.android.weatherview.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.*
import kr.groo.android.weatherview.domain.WeatherFunction
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherParam
import kr.groo.android.weatherview.ui.item.RainDropping
import kr.groo.android.weatherview.ui.item.SnowFlaking

class WeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private val weatherScope = CoroutineScope(Dispatchers.IO)
    }

    private lateinit var weatherKind: WeatherKind
    private lateinit var weatherParam: WeatherParam

    private var weatherItemList: Array<WeatherFunction>? = null
    private var weatherJob: Job? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        weatherItemList = when (weatherKind) {
            is WeatherKind.Snow -> SnowFlaking().createItem(weatherParam)
            is WeatherKind.Rain -> RainDropping().createItem(weatherParam)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        weatherItemList?.forEach { item ->
            canvas?.let { item.drawItem(it) }
        }
        weatherJob = weatherScope.launch {
            delay(5)
            invalidate()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        weatherJob?.cancel()
    }

    fun setWeatherData(weatherKind: WeatherKind, weatherParam: WeatherParam) {
        this.weatherKind = weatherKind
        this.weatherParam = weatherParam
    }
}