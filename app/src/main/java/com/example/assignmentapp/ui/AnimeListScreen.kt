package com.example.assignmentapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.model.Anime
import com.example.animeapp.viewmodel.AnimeListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(navController: NavController, viewModel: AnimeListViewModel = hiltViewModel()) {
    val animeList by viewModel.animeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Top Anime") })
        }
    ) { padding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Green)
            }
        } else {
            LazyColumn(contentPadding = padding) {
                items(animeList) { anime ->
                    AnimeItem(anime = anime) {
                        navController.navigate("anime_detail/${anime.mal_id}")
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeItem(anime: Anime, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
            contentDescription = anime.title,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = anime.title, style = MaterialTheme.typography.titleMedium)
            Text("Episodes: ${anime.episodes ?: "N/A"}")
            Text("Rating: ${anime.score ?: "N/A"}")
        }
    }
}
