
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.R
import com.example.test_android.ui.theme.DangerColor
import com.example.test_android.ui.theme.DangerLigtColor
import com.example.test_android.ui.theme.GrayColor
import com.example.test_android.ui.theme.SecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountModal(
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        // 👇 Your modal content here
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Are you sure you want to Delete the Account?",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontFamily = PoppinsFont,
                fontWeight = FontWeight.Bold
                )
            Text(
                "We will verify you with a few questions",
                fontSize = 12.sp,
                fontFamily = PoppinsFont,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.width(120.dp),
                    border = BorderStroke(1.dp, PrimaryColor),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.width(120.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Yes, Delete")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavController) {

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
                    text = "Account",
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

                    //icon and title

                    Text(
                        "Privacy",
                        fontWeight = FontWeight.Bold,
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

                    //icon and title

                    Text(
                        "Change Number",
                        fontWeight = FontWeight.Bold,
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

                    //icon and title

                    Text(
                        "Request Account Info",
                        fontWeight = FontWeight.Bold,
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
                    modifier = Modifier.fillMaxWidth().clickable { showBottomSheet = true},
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    //icon and title

                    Text(
                        "Delete My Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = PoppinsFont,
                        color = DangerColor
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint = PrimaryColor
                    )

                }


            }
        }

    }

    // Show modal when needed
    if (showBottomSheet) {
        DeleteAccountModal(
            sheetState = sheetState,
            onDismiss = { showBottomSheet = false }
        )
    }
}











