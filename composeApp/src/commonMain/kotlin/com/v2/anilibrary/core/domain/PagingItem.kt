package com.v2.anilibrary.core.domain

data class PaginationItems<Value>(
    val data: List<Value>,
    val totalCount: Int,
    val hasNextPage: Boolean
)