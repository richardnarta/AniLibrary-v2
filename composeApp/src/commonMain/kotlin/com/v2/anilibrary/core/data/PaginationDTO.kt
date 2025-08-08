package com.v2.anilibrary.core.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationDTO (
    @SerialName("has_next_page") val nextPage: Boolean,
    @SerialName("last_visible_page") val lastPage: Int,
    @SerialName("current_page") val currentPage: Int,
    @SerialName("items") val items: PaginationItem,
)

@Serializable
data class PaginationItem (
    @SerialName("count") val totalPages: Int,
    @SerialName("total") val totalItems: Int,
    @SerialName("per_page") val itemCount: Int,
)