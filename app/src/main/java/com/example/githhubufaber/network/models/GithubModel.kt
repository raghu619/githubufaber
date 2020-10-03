package com.example.githhubufaber.network.models

import com.squareup.moshi.Json


class GithubModel : ArrayList<GithubModelItem>()

data class GithubModelItem(
    @Json(name ="full_name")
    val fullName: String,
    val id: String,
    val name: String,
    val owner: Owner
)

