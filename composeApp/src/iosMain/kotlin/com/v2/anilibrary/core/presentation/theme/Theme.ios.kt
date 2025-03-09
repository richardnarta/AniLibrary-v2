package com.v2.anilibrary.core.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

@Composable
actual fun getPlatformColorScheme(darkTheme: Boolean): ColorScheme {
    return if (darkTheme) darkScheme else lightScheme
}

@Composable
actual fun isDarkTheme(): Boolean {
    val currentTraitCollection = UIScreen.mainScreen.traitCollection
    return currentTraitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
}

@Composable
actual fun getPlatformTypography(): Typography {
    return MaterialTheme.typography
}