package com.example.ratemovies.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.ratemovies.core.presentation.util.errorPainter
import com.example.ratemovies.movie.presentation.movie_list.components.previewMovie
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun TitleRow(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
) {
    Row(
        modifier = modifier.offset(y = -(45).dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
        )

        ElevatedCard(
            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 12.dp,
                ),
        ) {
            SubcomposeAsyncImage(
                modifier =
                    Modifier
                        .width(60.dp)
                        .aspectRatio(2f / 3f),
                model = imageUrl,
                contentDescription = null,
                error = {
                    errorPainter
                },
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Composable
@PreviewLightDark
fun TitleRowPreview() {
    RateMoviesTheme {
        TitleRow(
            modifier = Modifier.height(300.dp),
            title = previewMovie.title,
            imageUrl = previewMovie.imageUrl,
        )
    }
}