package kr.groo.android.weatherview

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.groo.android.weatherview.databinding.ActivityMainBinding
import kr.groo.android.weatherview.domain.GetSnowItemsUseCase
import kr.groo.android.weatherview.domain.GetWeatherItemsUseCase
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherFalling

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var weatherFalling: WeatherFalling? = null
    var weatherKind: WeatherKind? = null
    var getWeatherItemsUseCase: GetWeatherItemsUseCase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            activity = this@MainActivity
            lifecycleOwner = this@MainActivity
        }

        weatherFalling = WeatherFalling.Builder()
            .fallingItemColor(Color.WHITE)
            .fallingItemFromTheSky(true)
            .build()

        weatherKind = WeatherKind.Snow()

        getWeatherItemsUseCase = GetSnowItemsUseCase()
    }
}
