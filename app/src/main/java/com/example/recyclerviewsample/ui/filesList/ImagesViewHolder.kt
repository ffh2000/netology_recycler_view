package com.example.recyclerviewsample.ui.filesList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsample.data.ImageItem
import com.example.recyclerviewsample.databinding.ImageItemBinding

/**
 * View holder должен при своем создании создавать на основе верстки элемент списка,
 * а за тем обеспечивать обновление данных в нем.
 */
class ImagesViewHolder(
    itemView: View,
    val onDeleteClickCallback: (item: ImageItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ImageItemBinding.bind(itemView)
    private lateinit var data: ImageItem

    /**
     * Метод для связи данных и визуальных объектов в текущем элементе списка
     * @param data ссылка на данные, которые надо привязать и показать
     */
    fun bind(data: ImageItem) {
        this.data = data
        binding.apply {
            image.setImageBitmap(data.image)
            imageNameTextView.text = data.name
            closeButton.setOnClickListener(::onDeleteClick)
        }
    }

    private fun onDeleteClick(v : View) {
        onDeleteClickCallback(data)
    }
}
