package kr.groo.android.weatherview.extension

import androidx.databinding.BindingAdapter
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherParam
import kr.groo.android.weatherview.ui.WeatherView

@BindingAdapter(value = ["weatherKind", "weatherParam"], requireAll = true)
fun WeatherView.setWeatherData(weatherKind: WeatherKind?, weatherParam: WeatherParam?) {
    if (weatherKind != null && weatherParam != null) {
        setWeatherData(weatherKind, weatherParam)
    }
}
