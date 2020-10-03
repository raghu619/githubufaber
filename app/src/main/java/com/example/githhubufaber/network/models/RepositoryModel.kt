package com.example.githhubufaber.network.models

import com.squareup.moshi.Json


data class RepositoryModel(
    @Json(name = "id")
    val Id :String ,
    val name :String,
    @Json(name = "full_name")
    val fullName :String,
    val description : String
)