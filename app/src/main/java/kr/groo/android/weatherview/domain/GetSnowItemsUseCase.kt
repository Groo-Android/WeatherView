package kr.groo.android.weatherview.domain

import android.graphics.PointF
import kr.groo.android.weatherview.ui.item.WeatherItem
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherFalling
import kr.groo.android.weatherview.ui.item.SnowItem
import kr.groo.android.weatherview.util.RandomUtil

class GetSnowItemsUseCase : GetWeatherItemsUseCase {

    override fun invoke(
        screenWidth: Int,
        screenHeight: Int,
        weatherKind: WeatherKind?,
        weatherFalling: WeatherFalling?
    ): Array<WeatherItem> {
        if (weatherKind == null || weatherFalling == null) return emptyArray()

        return Array(weatherFalling.fallingItemCount) {
            with(weatherFalling) {
                val size = if (weatherKind is WeatherKind.Snow) RandomUtil.getRandomFromUntil(weatherKind.minSize, weatherKind.maxSize) else 0F

                SnowItem(
                    screenWidth = screenWidth,
                    screenHeight = screenHeight,
                    itemSize = size,
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
