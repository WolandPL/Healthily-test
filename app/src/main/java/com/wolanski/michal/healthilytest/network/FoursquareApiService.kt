package com.wolanski.michal.healthilytest.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wolanski.michal.healthilytest.entities.Response
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.foursquare.com/"
private const val CLIENT_ID = "ZJP3DYEAG0JM5AKNCLT3GABHCPYVRCY0YVAKZQDU0PDV0TF1"
private const val CLIENT_SECRET = "A3N0FRPX0XICLSMLVCVDQ2ZPOO3Z1XM3BTCM2TWQT4TNN5EZ"
private const val AUTHENTICATION_PARAMS = "client_id=$CLIENT_ID&client_secret=$CLIENT_SECRET"
private const val API_DATE = "20200726"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FoursquareApiService {
    @GET("v2/venues/search?$AUTHENTICATION_PARAMS&v=$API_DATE")
    fun getVenues(@Query("near") name: String): Single<Response>
}

object FoursquareApi {
    val retrofitService: FoursquareApiService by lazy {
        retrofit.create(FoursquareApiService::class.java)
    }
}