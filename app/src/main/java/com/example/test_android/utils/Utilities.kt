package com.example.test_android.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.example.test_android.domain.ChatMessage
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

fun generateQRCode(content: String, size: Int = 512): Bitmap {
    val bitMatrix: BitMatrix = MultiFormatWriter().encode(
        content,
        BarcodeFormat.QR_CODE,
        size,
        size
    )

    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)

    for (x in 0 until size) {
        for (y in 0 until size) {
            bitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
        }
    }
    return bitmap
}

fun groupMessagesByDay(messages: List<ChatMessage>): Map<String, List<ChatMessage>> {
    val now = System.currentTimeMillis()
    val oneDay = 24 * 60 * 60 * 1000L

    return messages.groupBy { msg ->
        when {
            msg.timestamp >= now - oneDay -> "Today"
            msg.timestamp >= now - 2 * oneDay -> "Yesterday"
            msg.timestamp >= now - 8 * oneDay -> "7 days ago"
            else -> "Earlier"
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun formatTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}





fun formatTimestampToDateTime(timestamp: String): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC") // Backend sends in UTC
        val date = parser.parse(timestamp)

        // Example: "Sep 17, 2025 10:03 AM"
        val formatter = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault())
        formatter.format(date ?: Date())
    } catch (e: Exception) {
        "" // fallback if parsing fails
    }
}
