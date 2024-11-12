package com.example.ratemovies.movie.presentation.movie_detail

import android.R.attr.banner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.bottomInnerShadow
import com.example.ratemovies.core.presentation.util.errorPainter
import com.example.ratemovies.movie.presentation.models.defaultMovieUi
import com.example.ratemovies.movie.presentation.movie_detail.components.TitleRow
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieDetailScreen(
    state: MovieDetailState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailAction) -> Unit,
) {
    LazyColumn(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
    ) {
        // Banner
        item {
            AsyncImage(
                model = state.bannerUrl,
                contentDescription = "Banner Image",
                error = errorPainter,
                placeholder = errorPainter,
                fallback = errorPainter,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .bottomInnerShadow(),
            )
        }
        // Title and Image
        item {
            TitleRow(
                modifier =
                    Modifier
                        .padding(horizontal = Dimens.MovieDetailContainerPadding)
                        .background(Color.Transparent).offset(y = -(45.dp)),
                title = state.title,
                imageUrl = state.imageUrl,
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun MovieDetailScreenPreview() {
    RateMoviesTheme {
        MovieDetailScreen(
            state =
                MovieDetailState(
                    bannerUrl = defaultMovieUi().banner,
                    title = defaultMovieUi().title,
                    imageUrl = defaultMovieUi().imageUrl,
                ),
            modifier = Modifier,
            onAction = {},
        )
    }
}
