package com.example.recyclerviewsample.ui.filesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.recyclerviewsample.R
import com.example.recyclerviewsample.data.ImageItem

/**
 * Адаптер реализован по схеме паттерна "адаптер".
 * Предназначен для предоставления данных в RecyclerView
 * @param onDeleteClick обработчик нажатия на кнопку крестика
 */
class ImagesAdapter(
    private val onDeleteClick: (item: ImageItem) -> Unit
): ListAdapter<ImageItem, ImagesViewHolder>(ImagesDiffUtilCallback()) {

    /**
     * Обработчик, вызываемый при первом создании элемента списка (item).
     * Должен на основе входных данных создать нужный тип холдера и вернуть его.
     * @param parent View, который является владельцем для создаваемого элемента
     * @param viewType тип элемента. Если задавать элементам типы, то можно добиться
     *                 разное отображение для элементов разного типа. Например 0 - тип для
     *                 обычного элемента, 1 - для разделителей, 2 - для элементов с зеленым фоном
    * @return возвращает созданный экземпляр холдера
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false),
            onDeleteClick
        )
    }

    /**
     * Обработчик, вызываемый, когда надо связать данные из списка с эелементами списка.
     * @param holder холдер, созданный ранее в [onCreateViewHolder]
     * @param position положение элемента в списке отображаемых элементов, что бы можно было
     *                 найти данные, которые надо связать с данным холдером
     */
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Необязательная функция. Должна вернуть тип эелемента по его позиции.
     * Этот тип передается в [onCreateViewHolder], что бы понять какую верстку грузить.
     */
    override fun getItemViewType(position: Int): Int = ITEM_TYPE_DEFAULT

    /**
     * Метод удаляет элемент из списка и оповещает адаптер об удалении.
     */
    fun deleteItem(item: ImageItem) {
        val newList = currentList.toMutableList()
        val i = newList.indexOf(item)
        newList.removeAt(i)
        submitList(newList)
    }

    companion object {
        const val ITEM_TYPE_DEFAULT = 0
    }
}
