package com.example.animeapp.model

data class AnimeResponse(
    val data: List<Anime>
)

data class Anime(
    val mal_id: Int,
    val title: String,
    val images: Images,
    val episodes: Int?,
    val score: Double?
)

data class Images(
    val jpg: ImageUrl
)

data class ImageUrl(
    val image_url: String
)
