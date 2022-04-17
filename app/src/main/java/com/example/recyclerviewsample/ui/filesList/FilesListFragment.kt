package com.example.recyclerviewsample.ui.filesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewsample.R
import com.example.recyclerviewsample.data.FileImagesRepositoryImpl
import com.example.recyclerviewsample.data.ImageItem
import com.example.recyclerviewsample.data.ImageItemMapper
import com.example.recyclerviewsample.databinding.FragmentFilesListBinding
import com.example.recyclerviewsample.ext.viewBinding

/**
 * Фрагмент, отображающий список файлов.
 * У фрагмента 3 состояния:
 * - Отображение списка файлов
 * - Отображение загрузки
 * - Отображение ошибки
 */
class FilesListFragment : Fragment(R.layout.fragment_files_list) {

    /**
     * Биндинг делается делегатом из статьи на хабре
     * [https://habr.com/ru/post/501158/]
     */
    private val binding by viewBinding(FragmentFilesListBinding::bind)
    private lateinit var imagesAdapter: ImagesAdapter

    /**
     * Фабрика, умеющая создавать экземпляр [FilesListViewModel] с
     * передачей ему нужных ему зависимостей.
     * Задание на досуге: придумать такой вариант viewModels, что бы не
     * приходилось так сложно создавать фабрику. Что бы только тело метода create
     * Т.е. должно быть примерно так:
     *     private val viewModel by viewModels<FilesListViewModel>{
     *         return FilesListViewModel(
     *             FileImagesRepositoryImpl(requireContext())
     *         )
     *     }
     */
    private val filesListViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilesListViewModel(
                //создаю и передаю зависимость - ImagesRepository
                FileImagesRepositoryImpl(requireContext()),
                ImageItemMapper(),
            ) as T
        }
    }

    /**
     * Создание viewModel лениво.
     * Требуется поделючить fragment-ktx
     * Поскольку в [FilesListViewModel] требует получение зависимостей через
     * конструктор, создается фабрика, которая потом и создаст экземпляр
     * [FilesListViewModel] и передаст в него зависимость.
     */
    private val viewModel by viewModels<FilesListViewModel>{ filesListViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeStates()
    }

    /**
     * Метод подписывается слушать изменения состояний у фрагмента
     */
    private fun subscribeStates() {
        viewModel.fragmentState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is FragmentStates.Working -> showWorking(viewState)
                is FragmentStates.Loading -> showLoading()
                is FragmentStates.Error -> showError(viewState)
            }
        }
    }

    /**
     * Метод включает отображение рабочего состояния
     */
    private fun showWorking(state: FragmentStates.Working) {
        binding.workingLayer.root.visibility = View.VISIBLE
        binding.errorLayer.root.visibility = View.GONE
        binding.loadingLayer.root.visibility = View.GONE
        setupImagesList(state.imagesList)
    }

    /**
     * Метод включает отображение состояния загрузки
     */
    private fun showLoading() {
        binding.workingLayer.root.visibility = View.GONE
        binding.errorLayer.root.visibility = View.GONE
        binding.loadingLayer.root.visibility = View.VISIBLE
    }

    /**
     * Метод включает отображение состояния отображения ошибка
     */
    private fun showError(state: FragmentStates.Error) {
        binding.workingLayer.root.visibility = View.GONE
        binding.errorLayer.root.visibility = View.VISIBLE
        binding.loadingLayer.root.visibility = View.GONE
        binding.errorLayer.errorTextView.text = state.t.localizedMessage
    }

    /**
     * Метод создает адаптер и устанавливает для списка картинок
     * @param imagesList список картинок, для которого надо создать адаптер
     */
    private fun setupImagesList(imagesList: List<ImageItem>) {
        binding.workingLayer.imagesRecyclerView.apply {
            imagesAdapter = ImagesAdapter(::onDeleteImageClick).apply {
                submitList(imagesList)
            }
            adapter = imagesAdapter
        }
    }

    private fun onDeleteImageClick(item: ImageItem) {
        imagesAdapter.deleteItem(item)
    }
}
