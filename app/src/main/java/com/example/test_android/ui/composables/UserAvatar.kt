package com.example.test_android.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.SecondaryColor

@Composable
fun UserAvatar(
    photoUrl: String?,
    modifier: Modifier = Modifier
) {
    if (!photoUrl.isNullOrEmpty()) {
        AsyncImage(
            model = photoUrl,
            contentDescription = "User Avatar",
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    } else {
        Icon(
            imageVector = Icons.Filled.Person, // your fallback icon
            contentDescription = "Default Avatar",
            tint = PrimaryColor,
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(SecondaryColor)
                .padding(12.dp)
        )
    }
}
