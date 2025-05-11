package com.example.animeapp.model

data class AnimeDetailResponse(
    val data: AnimeDetail
)

data class AnimeDetail(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val genres: List<Genre>,
    val trailer: Trailer,
    val images: Images,
    val cast: List<Character>?
)

data class Genre(val name: String)
data class Trailer(val youtube_id: String?)
data class Character(val character: CharacterInfo)
data class CharacterInfo(val name: String)
