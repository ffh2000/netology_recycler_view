package com.example.recyclerviewsample.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.recyclerviewsample.model.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Класс с конкретной реализацией репозитория изображений.
 * В данном случае репозиторий реализует доступ к файлам
 * картинок из каталога assets
 * @property context Зависимость. Context нужен для
 *                   доступка к каталогу assets приложения
 */
class FileImagesRepositoryImpl(
    private val context: Context
) : ImagesRepository {
    override fun getImagesList(): Flow<List<ImageData>> = flow {
        val imagesList = mutableListOf<ImageData>()
        context.assets.list(imagesSubdir)?.asFlow()
            ?.collect { fileName ->
                val image = loadImage(fileName).single()
                imagesList.add(ImageData(image, fileName))
//                Log.d("Corutest", "imagesList.add: filename = $fileName; ${Thread.currentThread().name}")
            }
        emit(imagesList)
    }
        .flowOn(Dispatchers.IO)

    override fun loadImage(image: String): Flow<Bitmap> = flow {
//        Log.d("Corutest", "loadImage: ${Thread.currentThread().name}")
//        kotlinx.coroutines.delay(1000)
        emit(
            BitmapFactory.decodeStream(
                context.assets.open("$imagesSubdir/${image}")
            )
        )
    }
        .flowOn(Dispatchers.IO)

    companion object {
        /**
         * Подкаталог каталога assets, в котором лежат картинки
         */
        private const val imagesSubdir = "myimages"
    }
}
