package com.example.test_android.ui.screens.dashboard


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test_android.ui.composables.FriendListCard
import com.example.test_android.ui.theme.PrimaryColor
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.test_android.data.remote.model.FriendRequestDto
import com.example.test_android.ui.theme.DangerColor
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.viewmodel.FriendRequestViewModel
import com.example.test_android.ui.viewmodel.FriendUIEvent
import com.example.test_android.ui.viewmodel.RequestState


@Composable
fun FriendRequestCard (
    navController: NavController,
    request: FriendRequestDto,
    viewModel: FriendRequestViewModel
    ) {


    val context = LocalContext.current

    // Listen to one-time UI events (like toast messages)
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is FriendUIEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                FriendUIEvent.NavigateBack -> {
                    // Handle navigation here if needed
                    navController.popBackStack()
                }
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (request.fromUser.photo != null){
                AsyncImage(
                    model = request.fromUser.photo, // Reference your image here
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
                        text = request.fromUser.username,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFont,
                        fontSize = 16.sp,

                        )
                    Text(
                        text ="${request.fromUser.firstName} ${request.fromUser.lastName}",
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

            Button(
                modifier = Modifier.width(160.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DangerColor,  // Sets the button's background color
                    contentColor = Color.White   // Sets the color of the text/icon inside
                ),
                onClick = {
                    viewModel.declineFriendRequest(request.id)
                }) {

                Text("Decline Request")


            }

            Button(
                modifier = Modifier.width(160.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,  // Sets the button's background color
                    contentColor = Color.White   // Sets the color of the text/icon inside
                ),
                onClick = {
                    viewModel.acceptFriendRequest(request.id)
                }) {

                Text("Accept Request")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

    }
}

@Composable
fun FriendRequestScreen(
    navController: NavController,
    viewModel: FriendRequestViewModel = viewModel()
) {
    val itemsList = viewModel.requestList.collectAsState()

    LaunchedEffect(Unit){
        viewModel.listFriendRequest()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        topBar = {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Friend Requests",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = PoppinsFont,
                fontSize = 18.sp
            )
        }
    ) {

        innerPadding ->
        if(itemsList.value.isNotEmpty()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(itemsList.value) { item ->
                // This composable will be rendered for each item in the list
                FriendRequestCard(navController, item, viewModel)
            }

        } }else{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No friend request", color = PrimaryColor)
            }
        }
    }
}