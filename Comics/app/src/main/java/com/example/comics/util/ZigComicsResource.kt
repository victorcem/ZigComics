package com.example.comics.util

import com.example.comics.model.Comic

sealed class ZigComicsResource<T>(val resultData: T?, val message: String?) {
    class Success<T>(resultData: T) : ZigComicsResource<T>(resultData, null)
    class Error<T>(message: String) : ZigComicsResource<T>(null, message)
}

sealed class ZigComicsListEvent {
    class Success(val comicsList: List<Comic>) : ZigComicsListEvent()
    class Failure(val message: String?) : ZigComicsListEvent()
    object Loading : ZigComicsListEvent()
    object Empty : ZigComicsListEvent()
}