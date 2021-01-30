package com.koba.memestorage.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.koba.memestorage.data.MediaItem
import com.koba.memestorage.repo.MediaRepository

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val galleryRepository by lazy { MediaRepository() }

    private val _imagesLiveData = MutableLiveData<List<MediaItem>>()
    val imagesLiveData: LiveData<List<MediaItem>> get() = _imagesLiveData

    fun loadImages() =
        galleryRepository.fetchSavedImages(getApplication<Application>().contentResolver)
            .also {
                _imagesLiveData.postValue(it)
            }

}
