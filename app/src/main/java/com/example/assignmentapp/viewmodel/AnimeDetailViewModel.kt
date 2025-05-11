package com.example.animeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.model.AnimeDetail
import com.example.animeapp.network.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeDetail?>(null)
    val animeDetail: StateFlow<AnimeDetail?> = _animeDetail

    fun fetchAnimeDetail(animeId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getAnimeDetails(animeId)
                _animeDetail.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
