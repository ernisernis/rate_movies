package com.example.ratemovies.movie.presentation.movie_bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.models.toBookmarkMovieUi
import com.example.ratemovies.movie.presentation.movie_bookmark.components.MovieBookmarkListItem


@Composable
fun MovieBookmarkScreenRoot(
    viewModel: MovieBookmarkViewModel = hiltViewModel(),
    onMovieIdClick: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MovieBookmarkScreen(
        state = state,
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        onAction = { action ->
            when (action) {
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun MovieBookmarkScreen(
    state: MovieBookmarkState,
    modifier: Modifier = Modifier,
    onAction: (MovieBookmarkAction) -> Unit,
) {
   Column(
       modifier = modifier
   ) {
       // Title
       Text(
           text = stringResource(R.string.bookmarks),
           modifier = Modifier
               .padding(Dimens.MovieListContainerPadding),
           color = MaterialTheme.colorScheme.onBackground,
           style = MaterialTheme.typography.headlineMedium,
       )

       // Bookmark items
       LazyVerticalStaggeredGrid(
           columns = StaggeredGridCells.Adaptive(400.dp),
           verticalItemSpacing = Dimens.MovieBookmarkItemPaddingSmall,
           horizontalArrangement = Arrangement.spacedBy(Dimens.MovieBookmarkItemPaddingSmall),
           modifier = modifier
               .padding(vertical = Dimens.MovieBookmarkItemPaddingSmall),
       ) {
           items (
               items = state.bookmarkMovies,
               key = { it.id }
           ) { bookmarkMovie ->
               MovieBookmarkListItem(
                   modifier = Modifier
                       .animateItem()
                       .height(Dimens.MovieBookmarkItemHeight),
                   bookmarkMovieUi = bookmarkMovie.toBookmarkMovieUi(),
                   onClick = {
                       // TODO
                   },
                   onBookmarkClick = {
                       // TODO
                   }
               )
           }
       }
   }
}