package com.example.recyclerviewsample.data

import com.example.recyclerviewsample.common.Mapper
import com.example.recyclerviewsample.model.ImageData

/**
 * Маппер для перевода формата данных, получаемых из assets в формат для отоброажения
 * в списке.
 */
class ImageItemMapper: Mapper<ImageData, ImageItem> {
    override fun map(from: ImageData): ImageItem {
        return map(from, -1)
    }

    fun map(from: ImageData, index: Int = -1): ImageItem {
        return ImageItem(index, from.image, from.name)
    }

    override fun map(from: List<ImageData>): List<ImageItem> {
        val result = mutableListOf<ImageItem>()
        from.forEachIndexed { index, item ->
            result.add(map(item, index))
        }
        return result
    }
}
