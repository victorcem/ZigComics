package com.example.comics.domain

import com.example.comics.data.api.Api
import com.example.comics.model.ComicResponse
import com.example.comics.util.ZigComicsResource
import java.lang.Exception
import javax.inject.Inject

class ZigComicsRepository @Inject constructor(
    private val apiInstance: Api
) : ComicsRepository {

    override suspend fun getComics(): ZigComicsResource<ComicResponse> {
        return try {
            val response = apiInstance.getComics()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                ZigComicsResource.Success(result)
            } else {
                ZigComicsResource.Error(response.message())
            }

        } catch (e: Exception) {
            ZigComicsResource.Error(e.message ?: "An error occurred")
        }
    }
}