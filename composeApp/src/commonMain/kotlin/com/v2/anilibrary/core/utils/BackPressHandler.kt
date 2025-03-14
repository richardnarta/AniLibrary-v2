package com.v2.anilibrary.core.utils

import androidx.compose.runtime.Composable

@Composable
expect fun BackPressHandler(enabled: Boolean = true, onBackPressed: () -> Unit)