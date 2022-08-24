package com.edurda77.movie.utils

import android.graphics.Color

fun colorRating (rating: Int): Int {
    return if (rating<=15) {
        Color.RED
    } else if (rating in 16..40) {
        Color.YELLOW
    } else if (rating in 41..60) {
        Color.BLUE
    } else Color.GREEN
}