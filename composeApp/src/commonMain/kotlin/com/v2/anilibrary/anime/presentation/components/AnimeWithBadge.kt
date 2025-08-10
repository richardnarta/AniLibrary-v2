package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.core.utils.ImageLoader
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AnimeWithBadge(
    topBadge: String,
    bottomBadge: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .widthIn(max = 150.dp)
            .fillMaxWidth()
            .aspectRatio(0.67F)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            val backgroundImage = ImageLoader(imageUrl = imageUrl)

            when(backgroundImage.result) {
                null -> {
                    Image(
                        painter = painterResource(SharedRes.images.placeholder_portrait),
                        contentDescription = "Anime Cover",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }
                else -> {
                    Image(
                        painter = if (backgroundImage.result.isSuccess) backgroundImage.painter else painterResource(
                            SharedRes.images.placeholder_error_portrait),
                        contentDescription = "Anime Cover",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(0.8F)
                ),
                modifier = modifier
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = topBadge,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    lineHeight = 13.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }

            Card(
                shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(0.8F)
                ),
                modifier = modifier
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = bottomBadge,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    lineHeight = 13.sp,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 18.dp)
                )
            }
        }
    }
}