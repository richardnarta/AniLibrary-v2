package com.v2.anilibrary.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun RectangleFilterItem(
    text: String,
    textSize: TextUnit = 10.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    icon: Painter? = null,
    iconColorFilter: ColorFilter? = null,
    outlined: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(50.dp),
        colors = if (!outlined) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.8F)
        ) else CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = if (outlined) {
            BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        } else {
            null
        },
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onClick()
                }
        ) {
            if (icon != null) {
                Image(
                    icon,
                    contentDescription = "",
                    colorFilter = iconColorFilter,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
                        .size(16.dp)
                )
            }

            Text(
                text = text,
                color = if (!outlined) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = textSize,
                lineHeight = textSize,
                fontWeight = fontWeight,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .alpha(if (!outlined) 0.9F else 1F)
                    .padding(start = if (icon == null) 12.dp else 8.dp, end = 12.dp)
                    .padding(vertical = 4.dp)
            )
        }
    }
}