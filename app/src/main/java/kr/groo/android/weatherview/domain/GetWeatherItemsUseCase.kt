package kr.groo.android.weatherview.domain

import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.ui.item.WeatherItem

interface GetWeatherItemsUseCase {

    operator fun invoke(
        screenWidth: Int,
        screenHeight: Int,
        weatherKind: WeatherKind?,
        weatherFalling: WeatherFalling?
    ): Array<WeatherItem>
}
