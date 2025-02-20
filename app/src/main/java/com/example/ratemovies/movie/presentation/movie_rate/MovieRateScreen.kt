package com.example.ratemovies.movie.presentation.movie_rate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.RmIcons
import com.example.ratemovies.movie.presentation.components.DefaultIconContainer
import com.example.ratemovies.movie.presentation.components.PosterImage
import com.example.ratemovies.movie.presentation.models.toMovieUi
import com.example.ratemovies.movie.presentation.movie_list.components.movie
import com.example.ratemovies.movie.presentation.movie_rate.components.IconRate
import com.example.ratemovies.movie.presentation.movie_rate.components.RateErrorText
import com.example.ratemovies.ui.theme.RateMoviesTheme


@Composable
fun MovieRateScreenRoot(
    viewModel: MovieRateViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRateSubmitSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MovieRateScreen(
        state = state,
        modifier = Modifier
            .imePadding()
            .fillMaxSize(),
        onAction = { action ->
           when (action)  {
               MovieRateAction.OnBackClick -> onBackClick()
               else -> Unit
           }
            viewModel.onAction(action)
        }
    )

    LaunchedEffect(state.popBackStackFlag) {
        if (state.popBackStackFlag) {
            onRateSubmitSuccess()
        }
    }
}

@Composable
fun MovieRateScreen(
    modifier: Modifier = Modifier,
    state: MovieRateState,
    onAction: (MovieRateAction) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        // Banner
        AsyncImage(
            model = state.movieUi?.banner,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .blur(
                    radiusX = 15.dp,
                    radiusY = 15.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded,
                ),
            contentDescription = stringResource(R.string.description_banner),
            error = painterResource(R.drawable.placeholder_gradient),
            placeholder = painterResource(R.drawable.placeholder_gradient),
            fallback = painterResource(R.drawable.placeholder_gradient),
            contentScale = ContentScale.Crop,
        )

        // Close icon
        DefaultIconContainer(
            icon = RmIcons.Back,
            modifier = Modifier
                .statusBarsPadding()
                .padding(Dimens.MovieRateItemPaddingBig),
            onClick = {
                onAction(MovieRateAction.OnBackClick)
            }
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(Dimens.MovieRateComponentPadding)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(Dimens.MovieRateItemPaddingBig),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.movieUi?.imageUrl?.let {
                PosterImage(
                    url = it,
                    modifier = Modifier
                        .width(152.dp),
                )
            }
            // Title
            Text(
                text = if (state.selectedIndex != 0) {
                    state.selectedIndex.toString()
                } else {
                    stringResource(R.string.rate_title, state.movieUi?.title.toString())
                },
                modifier = Modifier
                    .padding(Dimens.MovieRateComponentPadding),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )

            // Star ratings
            Column {
               Row(
                   modifier = Modifier
                       .border(
                           width = if (state.indexError != null) 2.dp else 0.dp,
                           color = if (state.indexError != null) MaterialTheme.colorScheme.error else Color.Transparent,
                           shape = RoundedCornerShape(20)
                       )
                       .padding(Dimens.MovieRateItemPaddingNormal)
               ) {
                   (1..10).map { i ->
                       IconRate(
                           icon = if (i <= state.selectedIndex) RmIcons.StarRate else RmIcons.StarOutline,
                           modifier = Modifier.size(32.dp),
                           onClick = {
                               onAction(MovieRateAction.OnMovieRateClick(i))
                           }
                       )
                   }
               }
                if (state.indexError != null) {
                    RateErrorText(
                        text = state.indexError,
                        modifier = Modifier
                            .padding(top = Dimens.MovieRateItemPaddingSmall)
                            .align(Alignment.End)
                    )
                }
            }

            // Description
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.description,
                    onValueChange = {
                        onAction(MovieRateAction.OnDescriptionChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = Dimens.MovieRateDescriptionMinHeight),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.rate_description_hint),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    isError = state.descriptionError != null,
                )
                if (state.descriptionError != null) {
                    RateErrorText(
                        text = state.descriptionError,
                        modifier = Modifier
                            .padding(top = Dimens.MovieRateItemPaddingSmall)
                            .align(Alignment.End)
                    )
                }
            }

            // Button
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clickable {
                        state.movie?.let {
                            onAction(MovieRateAction.OnMovieRateSubmit(it))
                        }
                    }
                    .padding(Dimens.MovieRateComponentPadding)
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        RoundedCornerShape(20)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.rate),
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieRateScreenPreview() {
    RateMoviesTheme {
        var state by remember {
            mutableStateOf(
                MovieRateState(
                    movie = movie,
                    movieUi = movie.toMovieUi(),
                    selectedIndex = 0,
                ),
            )
        }

        MovieRateScreen(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onAction = {
                when (it) {
                    is MovieRateAction.OnMovieRateClick -> {
                        state = state.copy(
                            selectedIndex = it.index,
                        )
                    }
                    is MovieRateAction.OnDescriptionChange -> {
                        state = state.copy(
                            description = it.text,
                        )
                    }
                    else -> {}
                }
            },
        )
    }
}
