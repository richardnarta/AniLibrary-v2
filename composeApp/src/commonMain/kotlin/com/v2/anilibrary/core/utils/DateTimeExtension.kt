package com.v2.anilibrary.core.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.convertUTCToLocal(): kotlinx.datetime.LocalDateTime? {
    return try {
        val instant = Instant.parse(this)
        val localTimeZone = TimeZone.currentSystemDefault()
        instant.toLocalDateTime(localTimeZone)
    } catch (e: Exception) {
        null
    }
}

fun LocalDateTime?.formatFullDateTime(): String {
    return if (this != null) {
        val monthAbbreviation = this.month.name
            .substring(0, 3)
            .lowercase()
            .replaceFirstChar { it.uppercase() }

        val paddedHour = this.hour.toString().padStart(2, '0')
        val paddedMinute = this.minute.toString().padStart(2, '0')

        "${this.dayOfMonth} $monthAbbreviation ${this.year}, $paddedHour:$paddedMinute"
    } else {
        "Invalid date"
    }
}