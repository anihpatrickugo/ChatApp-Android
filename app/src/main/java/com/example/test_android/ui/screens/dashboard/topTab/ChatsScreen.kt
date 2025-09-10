package com.example.test_android.ui.screens.dashboard.topTab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.test_android.ui.composables.ChatListCard


@Composable
fun ChatsScreen(navController: NavController) {
    val itemsList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "item 6", "item 7", "item 8", "item 9", "1tem 10")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(itemsList) { item ->
            // This composable will be rendered for each item in the list
           ChatListCard(navController, id=1)
        }
    }
}