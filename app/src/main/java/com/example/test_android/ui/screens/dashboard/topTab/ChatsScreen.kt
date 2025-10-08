package com.example.test_android.ui.screens.dashboard.topTab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.test_android.ui.composables.ChatListCard
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.viewmodel.PrivatChatListViewModel

@Composable
fun ChatsScreen(
    navController: NavController,
    chatListViewModel: PrivatChatListViewModel = viewModel()
) {
    // ✅ collect StateFlow as Compose State
    val chats by chatListViewModel.chatList.collectAsState(initial = emptyList())

    // Fetch data once when screen opens
    LaunchedEffect(Unit) {
        chatListViewModel.fetchPrivateRooms()
    }
    
    if (chats.isNotEmpty()){

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(chats) { item ->
                // 🔹 Pass actual id from API, not hardcoded `1`
                ChatListCard(navController, item = item)
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No friend to chat with", color = PrimaryColor)
        }

    }
}
