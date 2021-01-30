package com.koba.memestorage.repo

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.provider.MediaStore
import com.koba.memestorage.data.GalleryItem
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class GalleryRepository {
    fun fetchSavedImages(contentResolver: ContentResolver): List<GalleryItem>{
        val images = mutableListOf<GalleryItem>()
        val projections = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED
        )
        
        val selection = "${MediaStore.Images.Media.DATE_ADDED} >= ?"
        
        val selectionArgs = arrayOf(
                dateToTimeStamp(day = 30, month = 1, year = 2021).toString()
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projections,
                selection,
                selectionArgs,
                sortOrder
        )?.use {cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val dateModifiedColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while(cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val dateModified = Date(TimeUnit.SECONDS.toMillis(cursor.getLong(dateModifiedColumn)))
                val displayName = cursor.getString(displayNameColumn)

                val contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                )

                val image = GalleryItem(
                        id,
                        displayName,
                        dateModified,
                        contentUri
                )
                images += image
            }
        }
        return images
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateToTimeStamp(day: Int, month: Int, year: Int): Long =
        SimpleDateFormat("dd.MM.yyyy").let { formatter ->
            TimeUnit.MICROSECONDS.toSeconds(formatter.parse("$day.$month.$year")?.time ?: 0)
        }
}
