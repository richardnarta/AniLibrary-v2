package com.v2.anilibrary.core.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
expect fun getPlatformColorScheme(darkTheme: Boolean): ColorScheme

@Composable
expect fun isDarkTheme(): Boolean

@Composable
expect fun getPlatformTypography(): Typography

@Composable
fun AppTheme(
    darkTheme: Boolean = isDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (dynamicColor) {
        getPlatformColorScheme(darkTheme)
    } else {
        if (darkTheme) darkScheme else lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = getPlatformTypography(),
        content = content
    )
}