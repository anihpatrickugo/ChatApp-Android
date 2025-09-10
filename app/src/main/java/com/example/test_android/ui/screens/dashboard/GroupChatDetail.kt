package com.example.test_android.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.test_android.ui.theme.GrayColor
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.SecondaryColor
import com.example.test_android.domain.ChatMessage
import com.example.test_android.domain.Sender
import com.example.test_android.R
import com.example.test_android.ui.composables.ChatList
import kotlinx.coroutines.launch





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupChatDetailScreen(navController: NavController, itemId: Int?) {
    val now = System.currentTimeMillis()
    val oneDay = 24 * 60 * 60 * 1000L

    var url = "https://imgs.search.brave.com/7sUwE1Pmzw__zy1tV5onYKawdRimhvaJ8omj815PK_M/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMjE5/MDg2NjMzOS9waG90/by9yZWQtc2lsdmVy/LWZlc3RpdmUtZm9u/dC1sZXR0ZXItcy0z/ZC53ZWJwP2E9MSZi/PTEmcz02MTJ4NjEy/Jnc9MCZrPTIwJmM9/ZzlHRWNVYXUwS2pQ/ZGpJMGpTQUgwSFlR/WThrbzQ5MEp1MmJa/Tjg4enpLUT0"


    val messages = listOf(
        ChatMessage(1, "Hey, how are you?", now - 1000 * 60 * 5, Sender.ME),
        ChatMessage(2, "I’m good, you?", now - 1000 * 60 * 3, Sender.OTHER),
        ChatMessage(3, "Doing great 👍", now - 1000 * 60 * 1, Sender.ME),
        ChatMessage(4, "Let’s meet tomorrow", now - oneDay + 1000 * 60 * 60 * 2, Sender.OTHER),
        ChatMessage(5, "Sure!", now - oneDay + 1000 * 60 * 60 * 5, Sender.ME)
    )

    var text by remember { mutableStateOf("") }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Whenever the messages size changes, scroll to the last one
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Card(
                    modifier = Modifier.weight(1f)
                        .clickable(onClick = { navController.navigate("group/$itemId")}),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row (
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        AsyncImage(
                            model = url, // Reference your image here
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentDescription = "user detail image",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = "Access Solutions",
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsFont,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                IconButton(
                    modifier = Modifier
                        .background(SecondaryColor)
                        .border(1.dp, PrimaryColor, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp)),
                    onClick = { },

                    ) {

                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"

                    )
                }

            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        },
        bottomBar = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Type a message…", fontSize = 12.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding() // pushes up when keyboard is open
                    .padding(16.dp),
                textStyle = TextStyle(fontSize = 14.sp),
                maxLines = 3,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.smileicon),
                        contentDescription = "Email icon"
                    )
                },
                trailingIcon ={
                    IconButton(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(end = 8.dp)
                            .background(PrimaryColor, CircleShape),
                        onClick = {},

                        ) {

                        Icon(
                            imageVector = Icons.Filled.ArrowUpward,
                            contentDescription = "More"

                        )
                    }
                },
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = SecondaryColor,
                    disabledContainerColor = GrayColor,
                    focusedIndicatorColor = PrimaryColor,
                    unfocusedIndicatorColor = Color.Gray,

                    )

            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ChatList(messages)
        }
    }
}



