package com.example.recyclerviewsample.model

import android.graphics.Bitmap

/**
 * Класс описывает запись об одной картинке
 */
data class ImageData(
    val image: Bitmap? = null,
    val name: String = ""
)
