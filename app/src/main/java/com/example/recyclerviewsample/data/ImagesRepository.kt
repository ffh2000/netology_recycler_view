package com.example.recyclerviewsample.data

import android.graphics.Bitmap
import com.example.recyclerviewsample.model.ImageData
import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс для работы репозитория изображений
 */
interface ImagesRepository {
    /**
     * Метод возвращает список изображений без загрузки изображений в память
     */
    fun getImagesList(): Flow<List<ImageData>>

    /**
     * Метод загружает изображение из файла и возвращает его
     * @param image имя изображения, которе надо загрузить
     */
    fun loadImage(image: String): Flow<Bitmap>
}
