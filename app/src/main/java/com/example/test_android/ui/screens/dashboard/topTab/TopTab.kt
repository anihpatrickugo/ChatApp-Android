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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.example.test_android.domain.tabItems
import com.example.test_android.domain.TabItem
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.screens.dashboard.topTab.HomeScreen
import com.example.test_android.ui.screens.dashboard.topTab.ProfileScreen
import com.example.test_android.ui.screens.dashboard.topTab.SettingsScreen
import com.example.test_android.ui.theme.PrimaryColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopTabsScreen() {
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.SpaceBetween
        ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
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
               IconButton(onClick = {
                   print("clicked icon")
               }) {
                   Icon(
                       imageVector = Icons.Default.Search,
                       contentDescription = "Search"

                   )
               }

               IconButtont(
                   modifier = Modifier.background(Color.Red),
                   onClick = {
                   print("clicked icon")
               }) {
                   Icon(
                       imageVector = Icons.Default.MoreVert,
                       contentDescription = "More"

                   )
               }
           }

        }
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
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
            pageCount = 3,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Makes the pager fill the remaining space
        ) {
                page ->
            when (tabItems[page]) {
                is TabItem.Home -> HomeScreen()
                is TabItem.Profile -> ProfileScreen()
                is TabItem.Settings -> SettingsScreen()
            }
        }
    }
}