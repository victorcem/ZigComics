package com.example.comics

import com.example.comics.data.api.Api
import com.example.comics.domain.ComicsRepository
import com.example.comics.util.ZigComicsDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@UninstallModules(Api::class)
@HiltAndroidTest
class MainHiltTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeComicsRepository: ComicsRepository


//    @BindValue
//    val fakeComicsRepository = FakeMarvelComicsRepository().getMarvelComics().resultData

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun someTest() = runBlocking {
        Assert.assertTrue(fakeComicsRepository.getComics().resultData != null)
    }

    @Module
//    @TestInstallIn(
//        components = [SingletonComponent::class],
//        replaces = [MarvelComicsModule::class]
//    )
    @InstallIn(SingletonComponent::class)
    interface TestMarvelComicsModule {

        @Binds
        fun provideMarvelComicsRepository(fakeRepository: FakeComicsRepository): ComicsRepository

        companion object {
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
}