package com.example.recyclerviewsample.ui.filesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewsample.data.ImageItemMapper
import com.example.recyclerviewsample.data.ImagesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Класс ViewModel, обеспечивающий логику работы с картинками
 * @param imagesRepository репозиторий изображений. Подключается как
 *                         внешняя зависимость через конструктор
 */
class FilesListViewModel(
    private val imagesRepository: ImagesRepository,
    private val imagesMapper: ImageItemMapper,
) : ViewModel() {

    /**
     * Текущее состояние фрагмента.
     * Приватное т.к. снаружи напрямую никто не может его менять.
     */
    private val _fragmentState: MutableLiveData<FragmentStates> = MutableLiveData()

    /**
     * Текущее состояние фрагмента для доступа снаружи. Снаружи его можно только читать.
     */
    val fragmentState: LiveData<FragmentStates> = _fragmentState

    init {
        loadImages()
    }

    /**
     * Метод посылает событие о смене состояния фрагмента
     */
    private fun changeState(newState: FragmentStates) {
        _fragmentState.value = newState
    }

    /**
     * Метод загружает список изображений
     */
    private fun loadImages() {
        viewModelScope.launch {
            changeState(FragmentStates.Loading)
            imagesRepository.getImagesList()
                .map {
//                    Log.d("Corutest", "loadImages mapper: ${Thread.currentThread().name}")
                    imagesMapper.map(it)
                }
                .flowOn(Dispatchers.IO)
                .collect {
//                    Log.d("Corutest", "loadImages collect: ${Thread.currentThread().name}")
                    changeState(FragmentStates.Working(it))
                }
        }
    }
}
