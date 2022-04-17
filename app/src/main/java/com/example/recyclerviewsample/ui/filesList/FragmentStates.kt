package com.example.recyclerviewsample.ui.filesList

import com.example.recyclerviewsample.common.IViewState
import com.example.recyclerviewsample.data.ImageItem

/**
 * Возможные состояния фрагмента [FilesListFragment]
 */
sealed class FragmentStates: IViewState {
    /**
     * Состояние загрузки
     */
    object Loading : FragmentStates()

    /**
     * Состояние ошибки
     */
    class Error(val t: Throwable) : FragmentStates()

    /**
     * Обычное рабочее состояние
     */
    class Working(val imagesList: List<ImageItem>) : FragmentStates()
}
