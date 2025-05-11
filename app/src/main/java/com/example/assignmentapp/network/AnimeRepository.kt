package com.example.animeapp.network

import com.example.animeapp.model.AnimeDetailResponse
import com.example.animeapp.model.AnimeResponse
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val api: AnimeApiService
) {
    suspend fun getTopAnime(): AnimeResponse = api.getTopAnime()
    suspend fun getAnimeDetails(id: Int): AnimeDetailResponse = api.getAnimeDetails(id)
}
