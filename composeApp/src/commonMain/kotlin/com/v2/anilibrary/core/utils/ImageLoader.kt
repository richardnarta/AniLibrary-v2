package com.v2.anilibrary.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import coil3.compose.rememberAsyncImagePainter

data class ImageLoadResult(
    val painter: Painter,
    val result: Result<Painter>?
)

@Composable
fun ImageLoader(
    imageUrl: String?
): ImageLoadResult {
    var imageResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            imageResult = if (
                it.painter.intrinsicSize.width > 1 &&
                it.painter.intrinsicSize.height > 1
            ) {
                Result.success(it.painter)
            } else {
                Result.failure(Throwable(message = "Invalid Image"))
            }
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageResult = Result.failure(it.result.throwable)
        }
    )

    return ImageLoadResult(painter, imageResult)
}