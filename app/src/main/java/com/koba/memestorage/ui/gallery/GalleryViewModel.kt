package com.koba.memestorage.ui.gallery

import android.app.Application
import androidx.lifecycle.ViewModel
import com.koba.memestorage.data.GalleryItem
import com.koba.memestorage.repo.GalleryRepository

class GalleryViewModel : ViewModel() {

    private val galleryRepository by lazy { GalleryRepository() }
    lateinit var application : Application

    fun loadImages() : List<GalleryItem>{
        return galleryRepository.fetchSavedImages(application.contentResolver)
    }

}
