package com.v2.anilibrary

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.v2.anilibrary.anime.presentation.detail.DetailAction
import com.v2.anilibrary.anime.presentation.detail.DetailState
import com.v2.anilibrary.anime.presentation.detail.screens.DetailScreen
import com.v2.anilibrary.core.presentation.theme.AppTheme
import com.v2.anilibrary.core.utils.DummyAnime

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AnimePreview(
    modifier: Modifier = Modifier
) {
    AppTheme {
        DetailScreen(
            dummyAnime = DummyAnime.getAnime(),
            dummyCharacter = DummyAnime.getAnimeCharacter(),
            dummyTrailer = DummyAnime.getAnimeTrailer(),
            dummyReview = DummyAnime.getAnimeReview(),
            state = DetailState(),
            onAction = { action ->
                when (action) {
                    is DetailAction.LastScrollPosition -> Unit
                    is DetailAction.OverflowSynopsis -> Unit
                    is DetailAction.ViewMoreSynopsis -> Unit
                }
            }
        )
    }
}

