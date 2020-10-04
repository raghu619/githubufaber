package com.example.githhubufaber.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GithubModelItem(
    @Json(name ="full_name")
    val fullName: String,
    val id: String,
    val name: String,
    val owner: Owner,
    val private : Boolean,
    val description : String?

):Parcelable

