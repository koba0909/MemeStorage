package com.koba.memestorage.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.koba.memestorage.data.GalleryItem
import com.koba.memestorage.repo.GalleryRepository

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val galleryRepository by lazy { GalleryRepository() }

    fun loadImages() : List<GalleryItem>{
        return galleryRepository.fetchSavedImages(getApplication<Application>().contentResolver)
    }

}
