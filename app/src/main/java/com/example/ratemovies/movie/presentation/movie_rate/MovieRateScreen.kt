package com.example.ratemovies.movie.presentation.movie_rate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.components.PosterImage
import com.example.ratemovies.movie.presentation.models.defaultMovieUi
import com.example.ratemovies.movie.presentation.movie_rate.components.IconRate
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieRateScreen(
    modifier: Modifier = Modifier,
    state: MovieRateState,
    onAction: (MovieRateAction) -> Unit,
) {
    Box(
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.background),
    ) {
        // Banner
        AsyncImage(
            model = state.bannerUrl,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .blur(
                        radiusX = 15.dp,
                        radiusY = 15.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded,
                    ),
            contentDescription = "Banner image",
            error = painterResource(R.drawable.banner1280wpreview),
            placeholder = painterResource(R.drawable.banner1280wpreview),
            fallback = painterResource(R.drawable.banner1280wpreview),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier =
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(Dimens.MovieRateComponentPadding)
                    .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(Dimens.MovieRateContainerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PosterImage(
                url = state.imageUrl,
                modifier = Modifier.width(152.dp),
            )
            // Title
            Text(
                text = "How would you rate ${state.title}?",
                modifier =
                    Modifier
                        .padding(Dimens.MovieRateComponentPadding),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
            // Star ratings
            Row {
                (1..10).map { i ->
                    IconRate(
                        icon = if (i <= state.selectedIndex) Icons.Default.Star else Icons.Default.StarOutline,
                        modifier = Modifier.size(32.dp),
                        onClick = { onAction(MovieRateAction.OnRateClick(i)) },
                    )
                }
            }
            // Button
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(0.8f)
                        .padding(Dimens.MovieRateComponentPadding)
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(20)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Rate",
                    modifier =
                        Modifier
                            .padding(vertical = 8.dp)
                            .clickable(enabled = true) {
                            },
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun MovieRateScreenPreview() {
    RateMoviesTheme {
        var state by remember {
            mutableStateOf(
                MovieRateState(
                    bannerUrl = defaultMovieUi().banner,
                    imageUrl = defaultMovieUi().imageUrl,
                    title = defaultMovieUi().title,
                    selectedIndex = 4,
                ),
            )
        }

        MovieRateScreen(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onAction = {
                when (it) {
                    is MovieRateAction.OnRateClick -> {
                        state =
                            state.copy(
                                selectedIndex = it.index,
                            )
                    }
                    else -> {}
                }
            },
        )
    }
}
