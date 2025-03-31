package com.v2.anilibrary.core.utils

import android.util.Log

actual fun customLog(message: String, tag: String) {
    Log.d("CUSTOM INFO", message)
}