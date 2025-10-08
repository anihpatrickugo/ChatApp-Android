package com.example.test_android.ui.screens.dashboard.topTab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.example.test_android.domain.tabItems
import com.example.test_android.domain.TabItem
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.screens.dashboard.topTab.ChatsScreen
import com.example.test_android.ui.screens.dashboard.topTab.GroupsScreen
import com.example.test_android.ui.screens.dashboard.topTab.FriendsScreen
import com.example.test_android.ui.theme.PrimaryColor
import androidx.compose.runtime.*
import com.algolia.instantsearch.compose.searchbox.SearchBoxState
import com.algolia.search.model.response.ResponseSearch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopTabsScreen(navController: NavController,
                  friendList : List<ResponseSearch.Hit>,
                  searchBoxState: SearchBoxState
) {
    val pagerState = rememberPagerState {
        tabItems.size
    }
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
        ) {

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top=4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
            text = "Chatify",
            fontWeight = FontWeight.ExtraBold,
            fontFamily = PoppinsFont,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
           )

           Row(){
//               IconButton(onClick = {
//                   print("clicked icon")
//               }) {
//                   Icon(
//                       imageVector = Icons.Default.Search,
//                       contentDescription = "Search"
//
//                   )
//               }

               IconButton(
                   onClick = {
                   expanded = !expanded

               }) {
                   Icon(
                       imageVector = Icons.Default.MoreVert,
                       contentDescription = "More"

                   )
               }

               // Dropdown menu
               DropdownMenu(
                   expanded = expanded,
                   onDismissRequest = { expanded = true }
               ) {
                   DropdownMenuItem(
                       text = { Text("Settings") },
                       onClick = {
                           expanded = false
                           navController.navigate("settings")
                       }
                   )
                   DropdownMenuItem(
                       text = { Text("New Requests") },
                       onClick = {
                           expanded = false
                           navController.navigate("friend-requests")
                       }
                   )
               }
           }

        }

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = PrimaryColor// Change this to your desired color
                )

            }
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = PrimaryColor,
                    unselectedContentColor = Color.Black,
                    text = { Text(
                        item.title,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = PoppinsFont
                        )
                           },

                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Makes the pager fill the remaining space
        ) {
                page ->
            when (tabItems[page]) {
                is TabItem.Chats -> ChatsScreen(navController)
                is TabItem.Groups -> GroupsScreen(navController)
                is TabItem.Friends -> FriendsScreen(navController, friendList = friendList,
                    searchBoxState = searchBoxState)
                else -> {}
            }
        }
    }
}