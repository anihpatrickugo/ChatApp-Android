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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.test_android.data.remote.model.PrivateChatRoomDto
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.utils.formatTimestampToDateTime


@Composable
fun ChatListCard (navController: NavController, item: PrivateChatRoomDto) {

    val participant = item.participants.first()
    val photo = participant.photo

    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Apply the clickable modifier to the card
            .clickable(onClick = { navController.navigate("chat-detail/${item.id}") }),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (photo != null){
                AsyncImage(
                    model = photo, // Reference your image here
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentDescription = "user image",
                    contentScale = ContentScale.Crop
                )
            }else {
               Icon(
                   imageVector = Icons.Default.PersonPin,
                   modifier = Modifier
                       .size(60.dp)
                       .clip(CircleShape),
                   contentDescription = "user image"
               )
            }
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
                    verticalArrangement = Arrangement.spacedBy(1.dp)

                    ) {
                    Text(
                        text = participant.username,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFont,
                        fontSize = 16.sp
                    )

                    if (item.lastMessage != null){
                        val lastMessage = if (item.lastMessage.message.length > 25){
                            "${item.lastMessage.message.take(35)}..."
                        }else {
                            item.lastMessage.message
                        }


                        Text(
                            text = lastMessage,
                            fontFamily = PoppinsFont,
                            fontSize = 12.sp
                        )

                        Text(
                            text = formatTimestampToDateTime(item.lastMessage.timestamp),
                            fontFamily = PoppinsFont,
                            fontSize = 12.sp,

                            )

                    }

                }
            }
        }
    }
}