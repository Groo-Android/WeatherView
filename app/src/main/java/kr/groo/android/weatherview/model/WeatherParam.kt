package kr.groo.android.weatherview.model

data class WeatherParam(
    val fallingItemCount: Int? = 0,
    val fallingItemMinSpeed: Int? = 0,
    val fallingItemMaxSpeed: Int? = 0,
    val fallingItemMinAngle: Int? = 0,
    val fallingItemMaxAngle: Int? = 0,
    val fallingItemMinAlpha: Int? = 0,
    val fallingItemMaxAlpha: Int? = 0,
    val fallingItemColor: Int? = 0,
    val fallingItemFadeAnimation: Boolean? = false,
    val fallingItemBeforeLoad: Boolean? = false
)
