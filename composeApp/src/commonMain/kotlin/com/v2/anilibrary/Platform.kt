package com.v2.anilibrary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform