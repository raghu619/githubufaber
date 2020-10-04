package com.example.githhubufaber.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Owner(
    @Json(name ="avatar_url")
    val avatar_url :String
):Parcelable