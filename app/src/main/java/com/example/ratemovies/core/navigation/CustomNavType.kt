package com.example.ratemovies.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.ratemovies.movie.domain.MovieNavArgs
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val MovieNavType =
        object : NavType<MovieNavArgs>(
            isNullableAllowed = false,
        ) {
            override fun get(
                bundle: Bundle,
                key: String,
            ): MovieNavArgs? {
                return Json.decodeFromString(bundle.getString(key) ?: return null)
                bundle.getString(key)
            }

            override fun parseValue(value: String): MovieNavArgs {
                return Json.decodeFromString(Uri.decode(value))
            }

            override fun serializeAsValue(value: MovieNavArgs): String {
                return Uri.encode(Json.encodeToString(value))
            }

            override fun put(
                bundle: Bundle,
                key: String,
                value: MovieNavArgs,
            ) {
                bundle.putString(key, Json.encodeToString(value))
            }
        }
}