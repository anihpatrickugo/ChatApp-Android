package com.example.test_android.ui.composables

import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
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
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_android.data.remote.model.SearchUser
import com.example.test_android.ui.providers.LocalUserViewModel
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.viewmodel.FriendRequestViewModel
import com.example.test_android.ui.viewmodel.FriendUIEvent
import com.example.test_android.ui.viewmodel.PrivatChatListViewModel
import com.example.test_android.ui.viewmodel.RequestState
import com.example.test_android.ui.viewmodel.UserState


@Composable
fun FriendListCard (
    navController: NavController,
    user: SearchUser,
    viewModel: FriendRequestViewModel = viewModel(),
    chatsViewModel: PrivatChatListViewModel = viewModel(),
    ) {

    val requestState = viewModel.requestState.collectAsState()
    val context = LocalContext.current




    val isFriend = chatsViewModel.chatList.value.any { chat ->
        chat.participants[0].username == user.username
    }

    // Listen to one-time UI events (like toast messages)
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is FriendUIEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                FriendUIEvent.NavigateBack -> {
                    // Handle navigation here if needed
                }
            }
        }
    }


    Card(
        modifier = Modifier
            .fillMaxWidth(),
            // Apply the clickable modifier to the card
//            .clickable(onClick = { navController.navigate("profile/$id")}),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (user.getPhoto != null){
                AsyncImage(
                    model = user.getPhoto, // Reference your image here
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentDescription = "user image",
                    contentScale = ContentScale.Crop
                )
            }else{
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
                    modifier = Modifier.fillMaxWidth()


                    ) {
                    Text(
                        text = user.username,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFont,
                        fontSize = 16.sp,

                        )
                    Text(
                        text = "${user?.firstName} ${user?.lastName}",
                        fontFamily = PoppinsFont,
                        fontSize = 10.sp,
                        )

                }

            }


        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            if(!isFriend){
                Button(
                    modifier = Modifier.width(400.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor,  // Sets the button's background color
                        contentColor = Color.White   // Sets the color of the text/icon inside
                    ),
                    onClick = {
                        viewModel.sendFriendRequest(user.objectID)
                    }) {
                    when (val state = requestState.value) {
                        is RequestState.Idle -> Text("Send Friend Request") // This is the text displayed on the button
                        is RequestState.Loading -> CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        else -> Text("Send Friend Request")
                    }

                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp)) 

    }   
}