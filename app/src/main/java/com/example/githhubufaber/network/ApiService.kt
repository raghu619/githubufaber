package com.example.githhubufaber.network


import android.os.Build
import com.example.githhubufaber.BuildConfig
import com.example.githhubufaber.network.models.ContributorModel
import com.example.githhubufaber.network.models.GithubModelItem
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://api.github.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface ApiService {


    @GET("repositories")
    fun fetchGithubRepos(): Deferred<List<GithubModelItem>>

    @Headers(
        "access_token:" + BuildConfig.ACESS_TOKEN
    )
    @GET("repos/{user}/{name}/contributors")
    fun fetchContributorsData(
        @Path(value = "user") user: String,
        @Path(value = "name") name: String
    ): Deferred<List<ContributorModel>>


    @GET("users/{name}/repos")
    fun fetchUserRepos(@Path(value = "name") name: String): Deferred<List<GithubModelItem>>

}

object Api {

    val retrofitService: ApiService by lazy {

        retrofit.create(ApiService::class.java)
    }


}


