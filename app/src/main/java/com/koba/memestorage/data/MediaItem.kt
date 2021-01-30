package com.koba.memestorage.data

import android.net.Uri
import java.util.*

data class MediaItem(
    val id: Long,
    val name: String,
    val date: Date,
    val uri: Uri
)