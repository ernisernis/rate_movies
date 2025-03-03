package com.example.ratemovies.movie.presentation.movie_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieListLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(32.dp),
            color = MaterialTheme.colorScheme.onBackground,
            strokeWidth = 2.dp,
        )
    }
}

@Preview
@Composable
fun MovieListLoadingPreview() {
    RateMoviesTheme {
        MovieListLoading(
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}