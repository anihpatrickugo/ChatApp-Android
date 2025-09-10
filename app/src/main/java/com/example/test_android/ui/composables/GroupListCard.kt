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
fun GroupListCard (navController: NavController, id: Int) {
    var url = "https://imgs.search.brave.com/7sUwE1Pmzw__zy1tV5onYKawdRimhvaJ8omj815PK_M/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMjE5/MDg2NjMzOS9waG90/by9yZWQtc2lsdmVy/LWZlc3RpdmUtZm9u/dC1sZXR0ZXItcy0z/ZC53ZWJwP2E9MSZi/PTEmcz02MTJ4NjEy/Jnc9MCZrPTIwJmM9/ZzlHRWNVYXUwS2pQ/ZGpJMGpTQUgwSFlR/WThrbzQ5MEp1MmJa/Tjg4enpLUT0"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Apply the clickable modifier to the card
            .clickable(onClick = { navController.navigate("group-chat-detail/$id")}),
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
                            text = "Access Solutions",
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(
                            text = "james: ",
                            fontFamily = PoppinsFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,

                            )
                        Text(
                            text = "Hello Guys ",
                            fontFamily = PoppinsFont,
                            fontSize = 12.sp,

                            )

                    }

                }
            }

        }
    }
}