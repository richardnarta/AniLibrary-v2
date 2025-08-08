package com.v2.anilibrary.core.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.v2.anilibrary.presentation.theme.AppTypography

@Composable
actual fun getPlatformColorScheme(darkTheme: Boolean): ColorScheme {
    val context = LocalContext.current
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) darkScheme else lightScheme
    }
}

@Composable
actual fun isDarkTheme(): Boolean = isSystemInDarkTheme()

@Composable
actual fun getPlatformTypography(): Typography {
    return AppTypography
}