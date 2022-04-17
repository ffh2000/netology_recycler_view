package com.example.recyclerviewsample.data

import android.graphics.Bitmap

/**
 * Класс для хранения одного элемента списка
 */
data class ImageItem(
    val index: Int = -1,
    val image: Bitmap? = null,
    val name: String = "",
    val info: String = ""
)
