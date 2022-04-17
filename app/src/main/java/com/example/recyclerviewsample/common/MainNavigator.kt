package com.example.recyclerviewsample.common

/**
 * Интерфейс для нафигатора, который должен открывать разные
 * фрагменты в главном MainActivity
 */
interface MainNavigator {

    /**
     * Метод для открытия фрагмента со списком файлов изображений
     */
    fun openFilesListFragment()
}
