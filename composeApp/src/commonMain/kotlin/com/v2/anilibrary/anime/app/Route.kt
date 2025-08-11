package com.v2.anilibrary.anime.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object RouteGraph: Route

    @Serializable
    data object HomePage: Route

    @Serializable
    data class DetailPage(val animeId: Int): Route
}