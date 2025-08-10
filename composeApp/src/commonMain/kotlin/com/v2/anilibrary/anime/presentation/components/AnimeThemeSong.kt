package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.anime.domain.AnimeThemeSong
import com.v2.anilibrary.core.presentation.components.RectangleFilterItem

@Composable
fun AnimeThemeSong(
    type: String,
    song: AnimeThemeSong,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = "[$type]",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 20.sp,
            )

            Text(
                text = song.song,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Text(
            text = song.singer,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Row (
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            RectangleFilterItem(
                text = "Spotify",
                icon = SharedRes.images.spotify,
                outlined = true,
            )

            RectangleFilterItem(
                text = "Youtube",
                icon = SharedRes.images.youtube,
                outlined = true,
            )
        }
    }
}