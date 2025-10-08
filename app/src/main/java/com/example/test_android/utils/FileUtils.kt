package com.example.test_android.utils

import android.content.Context
import android.net.Uri
import java.io.File

fun Context.uriToFile(uri: Uri): File? {
    val inputStream = contentResolver.openInputStream(uri) ?: return null
    val file = File(cacheDir, "temp_profile_photo.jpg")
    file.outputStream().use { output ->
        inputStream.copyTo(output)
    }
    return file
}
