package com.example.comics.data.api

import com.example.comics.model.ComicResponse
import com.example.comics.util.ZigComicsConstants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String = ZigComicsConstants.TS,
        @Query("apikey") apikey: String = ZigComicsConstants.API_KEY,
        @Query("hash") hash: String = ZigComicsConstants.HASH
    ) : Response<ComicResponse>
}