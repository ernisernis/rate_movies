package com.example.ratemovies.di

import com.example.ratemovies.core.data.networking.HttpClientFactory
import com.example.ratemovies.movie.data.RemoteMovieDataSource
import com.example.ratemovies.movie.domain.MovieDataSource
import com.example.ratemovies.movie.presentation.movie_list.MovieListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteMovieDataSource).bind<MovieDataSource>()

    viewModelOf(::MovieListViewModel)
}
