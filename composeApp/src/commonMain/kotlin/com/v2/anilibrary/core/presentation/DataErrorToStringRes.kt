package com.v2.anilibrary.core.presentation

import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.core.domain.DataError

fun DataError.toUiText(): UiText {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> SharedRes.strings.error_disk_full
        DataError.Local.UNKNOWN -> SharedRes.strings.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> SharedRes.strings.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> SharedRes.strings.error_too_many_request
        DataError.Remote.NO_INTERNET -> SharedRes.strings.error_no_internet
        DataError.Remote.SERVER -> SharedRes.strings.error_unknown
        DataError.Remote.SERIALIZATION -> SharedRes.strings.error_serialization
        DataError.Remote.UNKNOWN -> SharedRes.strings.error_unknown
        else -> SharedRes.strings.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}