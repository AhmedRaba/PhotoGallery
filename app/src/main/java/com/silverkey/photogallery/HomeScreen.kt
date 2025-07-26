package com.silverkey.photogallery

import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.silverkey.domain.model.Photo
import com.silverkey.domain.utils.ResultState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val resultState by viewModel.photos.collectAsState()
    val isPaging by viewModel.isPaging.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        when (resultState) {
            is ResultState.Loading -> {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = Modifier
                        .align(androidx.compose.ui.Alignment.Center)
                )
            }

            is ResultState.Success -> {
                val photos = (resultState as ResultState.Success).data
                PhotoList(
                    photos = photos,
                    isPaging = isPaging,
                    onLoadMore = { viewModel.loadMorePhotos() }
                )
            }

            is ResultState.Error -> {
                Text(
                    "Error: ${(resultState as ResultState.Error).message}",
                    color = Color.Red,
                    modifier = Modifier
                        .align(androidx.compose.ui.Alignment.Center)
                        .padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun PhotoList(
    photos: List<Photo>,
    isPaging: Boolean,
    onLoadMore: () -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        items(photos.size) { index ->
            val photo = photos[index]

            if (index >= photos.size - 5) {
                LaunchedEffect(Unit) {
                    onLoadMore()
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column {
                    GlideImage(
                        url = photo.mediumUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((180..300).random().dp)
                    )
                }
            }
        }

        if (isPaging) {
            item {
                Text(
                    text = "Loading more...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun GlideImage(
    url: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    AndroidView(
        factory = {
            ImageView(it).apply {
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                )
                scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(context)
                    .load(url)
                    .into(this)
            }
        },
        modifier = modifier
    )
}