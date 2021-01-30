package com.koba.memestorage.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.koba.memestorage.data.MediaItem
import com.koba.memestorage.repo.MediaRepository
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val galleryRepository by lazy { MediaRepository() }

    private val _imagesLiveData = MutableLiveData<List<MediaItem>>()
    val imagesLiveData: LiveData<List<MediaItem>> get() = _imagesLiveData

    fun loadImages() =
        viewModelScope.launch {
            galleryRepository.fetchSavedImages(getApplication<Application>().contentResolver)
                .also {
                    _imagesLiveData.postValue(it)
                }
        }

}
