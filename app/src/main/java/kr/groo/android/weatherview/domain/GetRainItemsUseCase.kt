package kr.groo.android.weatherview.domain

import android.graphics.PointF
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.ui.item.RainItem
import kr.groo.android.weatherview.ui.item.WeatherItem
import kr.groo.android.weatherview.util.RandomUtil

class GetRainItemsUseCase : GetWeatherItemsUseCase {

    override fun invoke(
        screenWidth: Int,
        screenHeight: Int,
        weatherKind: WeatherKind?,
        weatherFalling: WeatherFalling?
    ): Array<WeatherItem> {
        if (weatherKind == null || weatherKind !is WeatherKind.Rain || weatherFalling == null) return emptyArray()

        return Array(weatherFalling.fallingItemCount) {
            with(weatherFalling) {
                val strokeWidth = RandomUtil.getRandomFromUntil(weatherKind.minWidth, weatherKind.maxWidth)
                val strokeHeight = RandomUtil.getRandomFromUntil(weatherKind.minHeight, weatherKind.maxHeight)

                RainItem(
                    screenWidth = screenWidth,
                    screenHeight = screenHeight,
                    itemWidth = strokeWidth,
                    itemHeight = strokeHeight,
                    itemSpeed = RandomUtil.getRandomFromUntil(
                        fallingItemSpeed.first,
                        fallingItemSpeed.second
                    ),
                    itemAngle = Math.toRadians((RandomUtil.getRandomFromUntil(
                        fallingItemAngle.first,
                        fallingItemAngle.second
                    ) * RandomUtil.getRandomSign()).toDouble()),
                    itemRotateSize = RandomUtil.getRandomFromUntil(
                        fallingItemRotate.first,
                        fallingItemRotate.second
                    ),
                    itemPoint = PointF(RandomUtil.getRandomUntil(screenWidth.toFloat()), if (fallingItemFromTheSky) (-screenHeight / 2).toFloat() else 0F),
                    weatherFalling = this
                )
            }
        }
    }
}
