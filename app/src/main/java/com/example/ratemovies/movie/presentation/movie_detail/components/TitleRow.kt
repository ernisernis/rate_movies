package com.example.ratemovies.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.components.PosterImage
import com.example.ratemovies.movie.presentation.models.toMovieUi
import com.example.ratemovies.movie.presentation.movie_list.components.movie
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun TitleRow(
    modifier: Modifier = Modifier,
    title: String?,
    imageUrl: String?,
) {
    Row(
        modifier = modifier
            .offset(y = -(45).dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        title?.let {
            Text(
                text = it,
                modifier = Modifier
                    .weight(1f),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )
        }

        imageUrl?.let {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 12.dp,
                ),
            ) {
                PosterImage(
                    modifier = Modifier
                        .width(60.dp),
                    url = it,
                )
            }
        }

    }
}

@Preview
@Composable
fun TitleRowPreview() {
    val movieUi = movie.toMovieUi()
    RateMoviesTheme {
        TitleRow(
            modifier = Modifier
                .padding(Dimens.MovieDetailContainerPadding)
                .height(300.dp),
            title = movieUi.title,
            imageUrl = movieUi.imageUrl,
        )
    }
}