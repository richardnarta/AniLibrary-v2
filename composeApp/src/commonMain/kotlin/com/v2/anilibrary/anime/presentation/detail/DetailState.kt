package com.v2.anilibrary.anime.presentation.detail

import com.v2.anilibrary.core.presentation.UiText

data class DetailState (
    val errorMessage: UiText? = null,

    val scrollPosition: Int = 0,
    val synopsisIsExpanded: Boolean = false,
    val synopsisIsOverflowed: Boolean = false,
)