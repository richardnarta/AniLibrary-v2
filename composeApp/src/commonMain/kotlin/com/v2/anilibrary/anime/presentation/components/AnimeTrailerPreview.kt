package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.anime.domain.AnimeTrailer
import com.v2.anilibrary.core.utils.ImageLoader
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AnimeTrailerPreview(
    trailer: AnimeTrailer,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .width(160.dp)
    ) {
        OutlinedCard(
            shape = RoundedCornerShape(25.dp),
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1.8F),
            onClick = {
                uriHandler.openUri(trailer.url)
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val coverImage = ImageLoader(imageUrl = trailer.imageCover)

                when(coverImage.result) {
                    null -> {
                        Image(
                            painter = painterResource(SharedRes.images.placeholder_portrait),
                            contentDescription = trailer.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    else -> {
                        Image(
                            painter = if (coverImage.result.isSuccess) coverImage.painter else painterResource(
                                SharedRes.images.placeholder_error_portrait),
                            contentDescription =  trailer.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }

                Image(
                    painter = painterResource(SharedRes.images.youtube),
                    contentDescription = "Youtube",
                    modifier = Modifier
                        .size(36.dp)
                        .padding(8.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }

        Text(
            text = trailer.title,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}