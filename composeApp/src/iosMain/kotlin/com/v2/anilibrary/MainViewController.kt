package com.v2.anilibrary

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import com.v2.anilibrary.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
) {
    MaterialTheme {
        App()
    }
}