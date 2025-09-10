package com.example.test_android.ui.screens.dashboard.QRCodeTab


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test_android.ui.composables.FriendListCard
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.utils.generateQRCode


@Composable
fun DynamicQRCode(content: String) {
    val qrBitmap = remember(content) { generateQRCode(content) }

    Image(
        bitmap = qrBitmap.asImageBitmap(),
        contentDescription = "QR Code",
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun MyCodeScreen(Controller: NavController) {

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        DynamicQRCode(content = "Ugochukwu")
        Text(
            text = "Your QR Code is private. If you share it with someone, then they can add you as a contact.",
            fontFamily = PoppinsFont,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}