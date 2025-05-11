package com.example.assignmentapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.viewmodel.AnimeDetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    animeId: Int,
    navController: NavController,
    viewModel: AnimeDetailViewModel = hiltViewModel()
) {
    val animeDetail by viewModel.animeDetail.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(animeId) {
        viewModel.fetchAnimeDetail(animeId)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Anime Detail") })
        }
    ) { padding ->
        animeDetail?.let { anime ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                val youtubeId = anime.trailer?.youtube_id
                if (!youtubeId.isNullOrEmpty()) {
                    AndroidView(
                        factory = { ctx ->
                            YouTubePlayerView(ctx).apply {
                                enableAutomaticInitialization = false
                                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        youTubePlayer.cueVideo(youtubeId, 0f)
                                    }
                                })
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                        contentDescription = anime.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(text = anime.title, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Episodes: ${anime.episodes ?: "N/A"}")
                Text(text = "Rating: ${anime.score ?: "N/A"}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Genres: ${anime.genres.joinToString { it.name }}")
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = anime.synopsis ?: "No synopsis available.")
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Green)
            }
        }
    }
}
