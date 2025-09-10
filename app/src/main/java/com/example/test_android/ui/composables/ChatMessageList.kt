package com.example.test_android.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_android.domain.ChatMessage
import com.example.test_android.domain.Sender
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.SecondaryColor
import com.example.test_android.utils.formatTime
import com.example.test_android.utils.groupMessagesByDay


@Composable
fun ChatList(messages: List<ChatMessage>) {
    val grouped = groupMessagesByDay(messages)


    Column(
        modifier = Modifier.fillMaxWidth(),

        ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            grouped.forEach { (day, msgs) ->
                // Divider Label
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(SecondaryColor, shape = MaterialTheme.shapes.small)
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = day,
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.DarkGray
                            )
                        }
                    }
                }

                // Messages for that day
                items(msgs) { message ->
                    ChatItem(message)
                }
            }
        }
    }

}

// -------- Chat Item --------
@Composable
fun ChatItem(message: ChatMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.sender == Sender.ME)
            Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .background(
                    if (message.sender == Sender.ME) Color(0xFFDCF8C6) else Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                .padding(6.dp),
            horizontalAlignment = if (message.sender == Sender.ME)
                Alignment.End else Alignment.Start
        ) {
            Text(message.text, fontSize = 12.sp,)
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = formatTime(message.timestamp),
                modifier = Modifier.padding(horizontal = 3.dp),
                fontSize = 8.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )

        }
    }
}