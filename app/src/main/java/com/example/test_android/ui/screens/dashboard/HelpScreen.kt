
package com.example.test_android.ui.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.DangerColor
import com.example.test_android.ui.theme.GrayColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),

                ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = PrimaryColor

                    )
                }

                Text(
                    text = "Help",
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = PoppinsFont,
                    fontSize = 18.sp,
                )

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
            // divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(GrayColor)
            )

            // links
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth().clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        "FAQ",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        fontFamily = PoppinsFont
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint = PrimaryColor
                    )

                }

                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(GrayColor)
                )

                //2
                Row(
                    modifier = Modifier.fillMaxWidth().clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        "Terms & Conditions",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        fontFamily = PoppinsFont
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint = PrimaryColor
                    )

                }

                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(GrayColor)
                )

                //3
                Row(
                    modifier = Modifier.fillMaxWidth().clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        "Privacy Policy",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        fontFamily = PoppinsFont
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint = PrimaryColor
                    )

                }

                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(GrayColor)
                )

                //4
                Row(
                    modifier = Modifier.fillMaxWidth().clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        "App Info",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        fontFamily = PoppinsFont
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint = PrimaryColor
                    )

                }

                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(GrayColor)
                )

            }
        }

    }
}











