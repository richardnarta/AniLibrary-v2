package com.v2.anilibrary

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.v2.anilibrary.anime.presentation.components.AnimePagerItemSkeleton
import com.v2.anilibrary.core.presentation.theme.AppTheme

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AnimePreview(
    modifier: Modifier = Modifier
) {
    AppTheme {
        AnimePagerItemSkeleton()
    }
}