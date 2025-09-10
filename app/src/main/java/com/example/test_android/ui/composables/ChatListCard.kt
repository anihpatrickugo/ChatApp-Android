package com.example.test_android.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_android.ui.theme.PoppinsFont


@Composable
fun ChatListCard (navController: NavController, id: Int) {
    var url = "https://imgs.search.brave.com/cxK8yYoMfgHkWGAMV5994P1TJD_KjjJr1flcmxioz00/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pMC53/cC5jb20vcGljanVt/Ym8uY29tL3dwLWNv/bnRlbnQvdXBsb2Fk/cy93b21hbi13aXRo/LXN1bi1nbGFzc2Vz/LWluLWZsb3dlci1m/aWVsZC1zdW1tZXIt/ZnJlZS1waG90by5q/cGc_dz02MDAmcXVh/bGl0eT04MA"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Apply the clickable modifier to the card
            .clickable(onClick = { navController.navigate("chat-detail/$id")}),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = url, // Reference your image here
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentDescription = "user image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),

                    ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "Ugochukwu",
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsFont,
                            fontSize = 16.sp,

                            )

                        Text(
                            text = "10: 43 am",
                            fontFamily = PoppinsFont,
                            fontSize = 12.sp,

                            )
                    }

                    Text(
                        text = "Whats up patrick how are you?",
                        fontFamily = PoppinsFont,
                        fontSize = 12.sp,

                        )

                }
            }

        }
    }
}