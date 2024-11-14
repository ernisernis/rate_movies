package com.example.ratemovies.movie.presentation.movie_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun DirectorRow(
    modifier: Modifier = Modifier,
    director: String?,
    writer: String?,
) {
    val directorText =
        if (director != null) {
            buildAnnotatedString {
                append("Director: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(director)
                }
            }
        } else {
            null
        }

    val writerText =
        if (writer != null) {
            buildAnnotatedString {
                append("Writer: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(writer)
                }
            }
        } else {
            null
        }

    val shouldShowVBar = director != null && writer != null

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (directorText != null) {
            Text(
                text = directorText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        if (shouldShowVBar) {
            VerticalDivider(
                modifier = Modifier.height(14.dp),
                thickness = 1.5.dp,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        if (writerText != null) {
            Text(
                text = writerText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun DirectorRowPreview() {
    RateMoviesTheme {
        DirectorRow(
            modifier = Modifier,
            director = "Josh Cooley",
            writer = "Andrew Barrer",
        )
    }
}