package com.example.ratemovies.core.presentation.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter

val errorPainter =
    BrushPainter(
        brush =
            Brush.verticalGradient(
                colors =
                    listOf(
                        Color.Gray.copy(alpha = 0.3f),
                        Color.Gray.copy(alpha = 0.7f),
                        Color.Gray,
                    ),
            ),
    )