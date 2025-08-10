package com.v2.anilibrary.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.core.utils.ImageLoader
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ScrimBackground(
    imageUrl: String,
    top: Boolean = true,
    bottom: Boolean = true,
    start: Boolean = true,
    end: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.33F)
    ) {
        val backgroundImage = ImageLoader(imageUrl = imageUrl)

        when(backgroundImage.result) {
            null -> {
                Image(
                    painter = painterResource(SharedRes.images.placeholder_portrait),
                    contentDescription = "Anime Background",
                    alpha = 0.4F,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .blur(4.dp)
                )
            }
            else -> {
                Image(
                    painter = if (backgroundImage.result.isSuccess) backgroundImage.painter else painterResource(
                        SharedRes.images.placeholder_error_portrait),
                    contentDescription = "Anime Background",
                    alpha = 0.4F,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                )
            }
        }

        if (bottom) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3F)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0f to Color.Transparent,
                                0.1F to MaterialTheme.colorScheme.background.copy(0.05F),
                                0.2F to MaterialTheme.colorScheme.background.copy(0.15F),
                                1.0f to MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )
        }

        if (top) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3F)
                    .align(Alignment.TopCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0f to MaterialTheme.colorScheme.background,
                                0.8F to MaterialTheme.colorScheme.background.copy(0.15F),
                                0.9F to MaterialTheme.colorScheme.background.copy(0.05F),
                                1.0f to Color.Transparent
                            )
                        )
                    )
            )
        }

        if (start) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3F)
                    .fillMaxHeight()
                    .align(Alignment.CenterStart)
                    .background(
                        brush = Brush.horizontalGradient(
                            colorStops = arrayOf(
                                0f to MaterialTheme.colorScheme.background,
                                0.8F to MaterialTheme.colorScheme.background.copy(0.15F),
                                0.9F to MaterialTheme.colorScheme.background.copy(0.05F),
                                1.0f to Color.Transparent
                            )
                        )
                    )
            )
        }

        if (end) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3F)
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)
                    .background(
                        brush = Brush.horizontalGradient(
                            colorStops = arrayOf(
                                0f to Color.Transparent,
                                0.1F to MaterialTheme.colorScheme.background.copy(0.05F),
                                0.2F to MaterialTheme.colorScheme.background.copy(0.15F),
                                1.0f to MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )
        }
    }
}