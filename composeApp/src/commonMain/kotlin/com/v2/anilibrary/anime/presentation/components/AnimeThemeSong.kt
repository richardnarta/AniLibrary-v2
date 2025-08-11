package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.anime.domain.AnimeThemeSong
import com.v2.anilibrary.core.presentation.components.RectangleFilterItem
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AnimeThemeSong(
    type: String,
    song: AnimeThemeSong,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val youtube = "https://www.youtube.com/results?search_query=${song.song}+by+${song.singer}"
    val spotify = "https://open.spotify.com/search/${song.song}"

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
                fontSize = 11.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Text(
            text = song.singer,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Row (
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            RectangleFilterItem(
                text = "Spotify",
                textSize = 12.sp,
                fontWeight = FontWeight.Medium,
                icon = painterResource(SharedRes.images.spotify),
                outlined = true,
                modifier = Modifier
                    .padding(end = 12.dp),
                onClick = {
                    uriHandler.openUri(spotify)
                }
            )

            RectangleFilterItem(
                text = "Youtube",
                textSize = 12.sp,
                fontWeight = FontWeight.Medium,
                icon = painterResource(SharedRes.images.youtube),
                outlined = true,
                onClick = {
                    uriHandler.openUri(youtube)
                }
            )
        }
    }
}