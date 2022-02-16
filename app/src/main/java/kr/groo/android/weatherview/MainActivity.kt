package kr.groo.android.weatherview

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.groo.android.weatherview.databinding.ActivityMainBinding
import kr.groo.android.weatherview.model.WeatherKind
import kr.groo.android.weatherview.model.WeatherParam

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var weatherKind: WeatherKind? = null
    var weatherParam: WeatherParam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            activity = this@MainActivity
            lifecycleOwner = this@MainActivity
        }

        weatherKind = WeatherKind.Snow()
        weatherParam = WeatherParam(
            fallingItemColor = Color.WHITE
        )
    }
}
