package com.example.animeapp.di

import com.example.animeapp.network.AnimeApiService
import com.example.animeapp.network.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimeApi(): AnimeApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(api: AnimeApiService): AnimeRepository {
        return AnimeRepository(api)
    }
}
