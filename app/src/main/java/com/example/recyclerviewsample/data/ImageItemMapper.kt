package com.example.recyclerviewsample.data

import com.example.recyclerviewsample.common.Mapper
import com.example.recyclerviewsample.model.ImageData

/**
 * Маппер для перевода формата данных, получаемых из assets в формат для отоброажения
 * в списке.
 */
class ImageItemMapper: Mapper<ImageData, ImageItem> {
    override fun map(from: ImageData): ImageItem {
        return ImageItem(from.image, from.name)
    }
}
