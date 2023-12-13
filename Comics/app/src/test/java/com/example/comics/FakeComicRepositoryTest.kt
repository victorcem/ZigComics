package com.example.comics

import com.example.comics.domain.ComicsRepository
import com.example.comics.model.Comic
import com.example.comics.model.ComicResponse
import com.example.comics.model.Data
import com.example.comics.util.ZigComicsResource
import javax.inject.Inject

class FakeComicsRepository @Inject constructor() : ComicsRepository {

    override suspend fun getComics(): ZigComicsResource<ComicResponse> {
        val fakeComic = Comic(
            3,
            "Wolverine",
        )

        return ZigComicsResource.Success(ComicResponse("", Data(10, listOf(fakeComic))))
    }
}