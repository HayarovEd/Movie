package com.edurda77.movie.utils

import android.graphics.Color
import com.edurda77.movie.entity.Genre
import kotlin.math.roundToInt

fun colorRating (rating: Int): Int {
    return if (rating <= 15) {
        Color.RED
    } else if (rating in 16..40) {
        Color.YELLOW
    } else if (rating in 41..60) {
        Color.BLUE
    } else Color.GREEN
}

fun popularityConvertor(rang: Double) = rang.roundToInt()

fun minutesToHours(minutes: Int): String {
    val first = minutes / 60
    val second = minutes % 60
    return "$first ч. $second мин."
}

fun ganreToString(genres: List<Genre>): String {
    val output = StringBuilder()
    val lastItem = genres.last()
    genres.forEach {
        if (it == lastItem) {
            output.append(it.name)
        } else {
            output.append(it.name).append(", ")
        }
    }
    return output.toString()
}