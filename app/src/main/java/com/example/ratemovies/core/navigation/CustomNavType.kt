package com.example.ratemovies.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.ratemovies.movie.presentation.models.MovieUi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val MovieUiType =
        object : NavType<MovieUi>(
            isNullableAllowed = false,
        ) {
            override fun get(
                bundle: Bundle,
                key: String,
            ): MovieUi? {
                return Json.decodeFromString(bundle.getString(key) ?: return null)
                bundle.getString(key)
            }

            override fun parseValue(value: String): MovieUi {
                return Json.decodeFromString(Uri.decode(value))
            }

            override fun serializeAsValue(value: MovieUi): String {
                return Uri.encode(Json.encodeToString(value))
            }

            override fun put(
                bundle: Bundle,
                key: String,
                value: MovieUi,
            ) {
                bundle.putString(key, Json.encodeToString(value))
            }
        }
}