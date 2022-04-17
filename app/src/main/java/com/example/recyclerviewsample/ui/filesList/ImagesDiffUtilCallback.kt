package com.example.recyclerviewsample.ui.filesList

import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerviewsample.data.ImageItem

class ImagesDiffUtilCallback : DiffUtil.ItemCallback<ImageItem>() {
    /**
     * Метод должен вернуть ture, если два эелемента на самом
     * деле являются одним и тем же элементом. Даже если содержимое
     * у элемента разное.
     */
    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
        oldItem.index == newItem.index

    /**
     * Метод должен вернуть true, если у двух элементов одинаковое содержимое.
     * Даже если их номера разные. Т.е. сверка по содержимому.
     */
    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
        oldItem.name == newItem.name
}
