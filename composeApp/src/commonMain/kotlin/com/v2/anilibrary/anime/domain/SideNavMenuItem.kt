package com.v2.anilibrary.anime.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.ui.graphics.vector.ImageVector

data class SideNavMenuItem(
    val title: String,
    val icon: ImageVector
)

val mainScreenMenu = listOf(
    SideNavMenuItem(
        title = "Menu",
        icon = Icons.Default.Done
    ),
)