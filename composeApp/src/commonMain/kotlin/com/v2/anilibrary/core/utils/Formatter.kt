package com.v2.anilibrary.core.utils

import androidx.compose.runtime.Composable
import com.v2.anilibrary.SharedRes
import dev.icerock.moko.resources.compose.stringResource

expect fun formatNumber(number: Int): String

@Composable
fun String?.formatSeason(): String {
    return if (this.isNullOrEmpty()) {
        "-"
    } else {
        val rawSeason = this.split(" ")
        val season = rawSeason[0].lowercase()
        val year = rawSeason[1]

        return when(season) {
            "winter" -> stringResource(SharedRes.strings.winter_with_year, year)
            "spring" -> stringResource(SharedRes.strings.spring_with_year, year)
            "summer" -> stringResource(SharedRes.strings.summer_with_year, year)
            "fall" -> stringResource(SharedRes.strings.fall_with_year, year)
            else -> "-"
        }
    }
}