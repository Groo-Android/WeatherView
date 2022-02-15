package kr.groo.android.weatherview.util

import java.util.Random

object RandomUtil {

    private val random = Random()

    fun getRandomFromUntil(from: Float, until: Float) = getRandomUntil(until - from) + from
    fun getRandomUntil(until: Float) = random.nextFloat() * until
    fun getRandomSign() = if (random.nextBoolean()) 1 else -1
}
