package com.v2.anilibrary.anime.presentation.detail

sealed interface DetailAction {
    data class LastScrollPosition(val y: Int): DetailAction
    data class ViewMoreSynopsis(val isExpanded: Boolean): DetailAction
    data class OverflowSynopsis(val isOverFlowed: Boolean): DetailAction
}