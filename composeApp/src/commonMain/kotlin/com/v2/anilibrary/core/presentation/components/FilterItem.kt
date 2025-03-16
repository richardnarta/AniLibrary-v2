package com.v2.anilibrary.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RectangleFilterItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.8F)
        ),
        modifier = modifier
            .padding(end = 8.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.background,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Medium,
            modifier = modifier
                .alpha(0.9F)
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}