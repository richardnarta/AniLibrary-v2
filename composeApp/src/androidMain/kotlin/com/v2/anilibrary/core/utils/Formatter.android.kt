package com.v2.anilibrary.core.utils

import java.text.NumberFormat
import java.util.Locale

actual fun formatNumber(number: Int): String {
    val formatter = NumberFormat.getInstance(Locale.getDefault())
    return formatter.format(number)
}