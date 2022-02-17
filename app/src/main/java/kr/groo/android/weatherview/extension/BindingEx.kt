package kr.groo.android.weatherview.extension

import androidx.databinding.BindingAdapter
import kr.groo.android.weatherview.domain.GetWeatherItemsUseCase
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.ui.WeatherView

@BindingAdapter(value = ["weatherFalling", "weatherKind", "getWeatherItemsUseCase"], requireAll = true)
fun WeatherView.setWeatherParams(
    weatherKind: WeatherKind?,
    weatherFalling: WeatherFalling?,
    getWeatherItemsUseCase: GetWeatherItemsUseCase?
) {
    weatherKind?.let { this.weatherKind = weatherKind }
    weatherFalling?.let { this.weatherFalling = weatherFalling }
    getWeatherItemsUseCase?.let { this.getWeatherItemsUseCase = getWeatherItemsUseCase }
}
