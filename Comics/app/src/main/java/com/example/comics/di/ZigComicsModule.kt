package com.example.comics.di

import com.example.comics.data.api.Api
import com.example.comics.domain.ComicsRepository
import com.example.comics.domain.ZigComicsRepository
import com.example.comics.util.ZigComicsConstants
import com.example.comics.util.ZigComicsDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ZigComicsModule {

    @Binds
    fun provideMarvelComicsRepository(repositoryMarvel: ZigComicsRepository): ComicsRepository

    companion object {
        @Singleton
        @Provides
        fun provideMarvelComicsServiceInstanceInterface(): Api =
            Retrofit.Builder()
                .baseUrl(ZigComicsConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

        @Singleton
        @Provides
        fun provideMarvelComicsDefaultRepository(apiInstance: Api): ZigComicsRepository =
            ZigComicsRepository(apiInstance)

        @Singleton
        @Provides
        fun provideMarvelComicsDispatchers(): ZigComicsDispatcherProvider = object :
            ZigComicsDispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main

            override val io: CoroutineDispatcher
                get() = Dispatchers.IO

            override val default: CoroutineDispatcher
                get() = Dispatchers.Default

            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined

        }
    }
}