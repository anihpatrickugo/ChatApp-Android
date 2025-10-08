package com.example.test_android.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.test_android.R
import com.example.test_android.data.remote.model.User
import com.example.test_android.ui.providers.LocalUserViewModel
import com.example.test_android.ui.theme.*
import com.example.test_android.ui.viewmodel.ChatDetailFriendViewModel
import com.example.test_android.ui.viewmodel.ChatMessage
import com.example.test_android.ui.viewmodel.ChatViewModel
import com.example.test_android.ui.viewmodel.UserState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen(
    navController: NavController,
    roomId: Int?,
    chatViewModel: ChatViewModel,
    chatDetailFriendViewModel: ChatDetailFriendViewModel = viewModel()
) {
    val userViewModel = LocalUserViewModel.current
    val userState by userViewModel.userState.collectAsState()


    // your friend detail(the person you are chatting with)
    val friendState by chatDetailFriendViewModel.friend.collectAsState()


    val allMessages by chatViewModel.messages.collectAsState()
    val messages = allMessages.filter { it.roomId == roomId } // 🔹 only messages for this room


    var text by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // 🔹 Load history when entering room
    LaunchedEffect(roomId) {
        if (roomId != null) {
            chatViewModel.loadHistory(roomId)
            chatDetailFriendViewModel.fetchFriendInRoom(roomId)
        }
    }

    // Auto scroll on new message
    LaunchedEffect(messages.size) {

        if (messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }


    when (userState){
        is UserState.Success -> {
            val user = (userState as UserState.Success).user

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                topBar = {
                    ChatTopBar(navController = navController, friendState = friendState)

                },
                bottomBar = {
                    ChatInput(
                        text = text,
                        onTextChange = { text = it },
                        onSendClick = {
                            if (text.isNotBlank()) {
                                chatViewModel.sendMessage(roomId!!, text, "admin")
                                text = "" // clear input
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(messages) { message ->
                            ChatBubble(message, user.username)
                        }
                    }
                }
            }

        }
        is UserState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UserState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(userState as UserState.Error).message}", color = Color.Red)
            }
        }

        UserState.Idle -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Idle…")
            }
        }
    }


}

@Composable
fun ChatTopBar(navController: NavController, friendState: UserState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
        }

        when(friendState){
            is UserState.Loading -> {
                Row(
                    modifier = Modifier.weight(1f)
                ){
                    Text("Loading")
                }
            }
            is UserState.Error -> {
                Row(
                    modifier = Modifier.weight(1f)
                ){
                    Text("Error", color = Color.Red)
                }

            }
            is UserState.Success -> {
                val friend = friendState.user
                // Fake avatar + name
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { navController.navigate("profile/${friend.id}") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (friend?.photo != null){
                        AsyncImage(
                            model = friend.photo, // Reference your image here
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentDescription = "user image",
                            contentScale = ContentScale.Crop
                        )
                    }else {
                        Icon(
                            imageVector = Icons.Default.PersonPin,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentDescription = "user icon"
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = friend.username,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFont,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

            }
            else -> Row(
                modifier = Modifier.weight(1f)
            ){
                Text("Error", color = Color.Red)
            }
        }
       
        IconButton(
            modifier = Modifier
                .background(SecondaryColor)
                .border(1.dp, PrimaryColor, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            onClick = { }
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
        }
    }
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
}

@Composable
fun ChatInput(text: String, onTextChange: (String) -> Unit, onSendClick: () -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text("Type a message…", fontSize = 12.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
            .padding(16.dp),
        textStyle = TextStyle(fontSize = 14.sp),
        maxLines = 3,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.smileicon),
                contentDescription = "Emoji"
            )
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier
                    .size(36.dp)
                    .padding(end = 8.dp)
                    .background(PrimaryColor, CircleShape),
                onClick = onSendClick
            ) {
                Icon(imageVector = Icons.Filled.ArrowUpward, contentDescription = "Send")
            }
        },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = SecondaryColor,
            disabledContainerColor = GrayColor,
            focusedIndicatorColor = PrimaryColor,
            unfocusedIndicatorColor = Color.Gray
        )
    )
}

@Composable
fun ChatBubble(message: ChatMessage, currentUser: String) {
    val isMe = message.user == currentUser || message.user == "me" // handle optimistic case too

    val bubbleColor = when {
        message.pending -> Color.LightGray.copy(alpha = 0.6f) // pending = faded gray
        isMe -> PrimaryColor
        else -> SecondaryColor
    }

    val arrangement = when {
        isMe -> Arrangement.End
        else -> Arrangement.Start
    }


    val textColor = if (isMe && !message.pending) Color.White else Color.Black

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = arrangement
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(4.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = message.message,
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = if (message.pending) FontWeight.Light else FontWeight.Normal
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (message.pending) {
                        Text(
                            text = "Sending…",
                            color = Color.DarkGray,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    } else {
                        Text(
                            text = message.timestamp.take(5), // HH:mm
                            color = textColor.copy(alpha = 0.7f),
                            fontSize = 10.sp,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

