package com.example.comics.domain

import com.example.comics.model.ComicResponse
import com.example.comics.util.ZigComicsResource


interface ComicsRepository {

    suspend fun getComics(): ZigComicsResource<ComicResponse>
}