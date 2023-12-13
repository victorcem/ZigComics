package com.example.comics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.domain.ComicsRepository
import com.example.comics.model.Comic
import com.example.comics.util.ZigComicsDispatcherProvider
import com.example.comics.util.ZigComicsListEvent
import com.example.comics.util.ZigComicsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZigComicsListViewModel @Inject constructor(
    private val repository: ComicsRepository,
    private val dispatchers: ZigComicsDispatcherProvider
) : ViewModel() {

    private val _comics = MutableStateFlow<ZigComicsListEvent>(ZigComicsListEvent.Empty)
    val comics: StateFlow<ZigComicsListEvent> = _comics

    fun onViewCreated() = viewModelScope.launch(dispatchers.io) {
        _comics.value = ZigComicsListEvent.Loading

        when (val zigComicsResponse = repository.getComics()) {
            is ZigComicsResource.Success -> {
                val zigComicsList = zigComicsResponse.resultData?.data?.results

                if (zigComicsList == null) {
                    _comics.value =
                        ZigComicsListEvent.Failure("Error. The heroes are saving the world right now.")
                } else {
                    _comics.value = ZigComicsListEvent.Success(zigComicsList)
                }
            }
            is ZigComicsResource.Error -> {
                _comics.value = ZigComicsListEvent.Failure(zigComicsResponse.message)
            }
        }
    }

    fun onZigCardSelect(comic: Comic){}
}