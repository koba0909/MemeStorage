package com.koba.memestorage.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.koba.memestorage.data.MediaItem
import com.koba.memestorage.repo.MediaRepository

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val galleryRepository by lazy { MediaRepository() }

    fun loadImages() : List<MediaItem>{
        return galleryRepository.fetchSavedImages(getApplication<Application>().contentResolver)
    }

}
