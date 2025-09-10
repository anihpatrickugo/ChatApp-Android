
package com.example.test_android.ui.screens.dashboard.QRCodeTab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_android.domain.QRCodeTabItem
import com.example.test_android.domain.qrCodeTabItems

import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.SecondaryColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QRCodeTab(navController: NavController) {

    val pagerState = rememberPagerState {
        qrCodeTabItems.size
    }
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    var url =
        "https://imgs.search.brave.com/cxK8yYoMfgHkWGAMV5994P1TJD_KjjJr1flcmxioz00/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pMC53/cC5jb20vcGljanVt/Ym8uY29tL3dwLWNv/bnRlbnQvdXBsb2Fk/cy93b21hbi13aXRo/LXN1bi1nbGFzc2Vz/LWluLWZsb3dlci1m/aWVsZC1zdW1tZXIt/ZnJlZS1waG90by5q/cGc_dz02MDAmcXVh/bGl0eT04MA"


    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ){
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryColor

                        )
                    }

                    Text(
                        text = "QR Code",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = PoppinsFont,
                        fontSize = 18.sp,
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ){
                    IconButton(
                        modifier = Modifier
                            .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondaryColor),
                        onClick = { },

                        ) {

                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "share",
                            tint= PrimaryColor

                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondaryColor),
                        onClick = { },

                        ) {

                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "more",
                            tint= PrimaryColor

                        )
                    }
                }


            }
        },

        ) { innerPadding ->
        // parent scrollable column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(16.dp))
            if (pagerState.currentPage == 0) {
                AsyncImage(
                    model = url,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentDescription = "user image",
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "Ugochukwu",
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = PoppinsFont,
                    fontSize = 16.sp,
                )
                Text(
                    text = "+92-857-5374-4789",
                    fontWeight = FontWeight.Bold,
                    fontFamily = PoppinsFont,
                    fontSize = 10.sp,
                )

                Spacer(modifier = Modifier.size(12.dp))
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
                qrCodeTabItems.forEachIndexed { index, item ->
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

            Column (
                modifier = Modifier.fillMaxWidth()
            ){

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // Makes the pager fill the remaining space
                ) {
                        page ->
                    when (qrCodeTabItems[page]) {
                        is QRCodeTabItem.MyCode -> MyCodeScreen(navController)
                        is QRCodeTabItem.ScanCode -> ScanCodeScreen(navController)
                        else -> {}
                    }
                }
            }

        }

    }


}











