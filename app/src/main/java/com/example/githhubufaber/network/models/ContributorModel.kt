package com.example.githhubufaber.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContributorModel(
    @Json(name = "login")
    val name: String,
    @Json(name = "avatar_url")
    val avatarUrl: String

) : Parcelable