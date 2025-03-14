package com.v2.anilibrary

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.v2.anilibrary.core.components.SearchBar
import dev.icerock.moko.resources.compose.stringResource

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = "",
        onQueryChange = {},
        placeholder = stringResource(SharedRes.strings.search_anime_hint_default),
        onImeSearch = {},
        onIconClicked = {},
    )
}