package com.koba.memestorage.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object PermissionUtils {
    fun haveStoragePermission(context: Context) =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    fun requestMediaPermission(fragment: Fragment, requestCode: Int) {
        if (!haveStoragePermission(fragment.requireContext())) {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            fragment.requestPermissions(permissions, requestCode)
        }
    }
}