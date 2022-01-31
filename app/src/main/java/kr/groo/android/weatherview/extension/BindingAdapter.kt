package kr.groo.android.weatherview.extension

import androidx.databinding.BindingAdapter
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherParam
import kr.groo.android.weatherview.ui.WeatherView

@BindingAdapter("weatherKind", "weatherParam")
fun WeatherView.setWeatherData(weatherKind: WeatherKind, weatherParam: WeatherParam) {
    setWeatherData(weatherKind, weatherParam)
}