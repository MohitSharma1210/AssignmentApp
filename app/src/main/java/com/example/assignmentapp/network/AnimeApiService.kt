package com.example.animeapp.network

import com.example.animeapp.model.AnimeDetailResponse
import com.example.animeapp.model.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("anime/{id}/full")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailResponse
}
