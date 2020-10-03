package com.example.githhubufaber.network.models

import com.squareup.moshi.Json


data class Owner(
    @Json(name ="avatar_url")
    val avatar_url :String
)