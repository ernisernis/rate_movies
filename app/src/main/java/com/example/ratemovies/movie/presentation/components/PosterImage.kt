package com.example.ratemovies.movie.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.errorPainter
import com.example.ratemovies.movie.presentation.models.defaultMovieUi
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun PosterImage(
    modifier: Modifier = Modifier,
    url: String,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
        ),
    ) {
        AsyncImage(
            modifier = modifier
                .aspectRatio(2f / 3f),
            model = url,
            contentDescription = "Poster image",
            placeholder = errorPainter,
            error = painterResource(R.drawable.poster780w1170hpreview),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview
@Composable
fun PosterImagePreview() {
    RateMoviesTheme {
        PosterImage(
            modifier = Modifier.width(160.dp),
            url = defaultMovieUi().imageUrl,
        )
    }
}