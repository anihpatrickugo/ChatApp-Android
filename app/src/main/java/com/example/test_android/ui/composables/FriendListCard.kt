package com.example.test_android.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor


@Composable
fun FriendListCard (navController: NavController, id: Int) {
    var url = "https://imgs.search.brave.com/DXs02k8eneKTjfemEuvqSlAMKONoFwJ9c2HJ_coLWi4/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93d3cu/aGVhZHNob3RwaG90/by5pby9fdmVyY2Vs/L2ltYWdlP3VybD0v/aW1hZ2VzL3BvcnRm/b2xpby9pbWctZ3Jp/ZC0xOS5wbmcmdz0z/MjAmcT0xMDA"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Apply the clickable modifier to the card
            .clickable(onClick = { navController.navigate("profile/$id")}),
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
                    modifier = Modifier.fillMaxWidth()


                    ) {
                    Text(
                        text = "MC Oluomo",
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFont,
                        fontSize = 16.sp,

                        )
                    Text(
                        text = "Always focus on the grind, never be caught unawre",
                        fontFamily = PoppinsFont,
                        fontSize = 10.sp,
                        )

                }






            }


        }
    }
}