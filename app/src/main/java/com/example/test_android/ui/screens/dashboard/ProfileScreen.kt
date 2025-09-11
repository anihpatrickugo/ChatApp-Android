
package com.example.test_android.ui.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbDownOffAlt
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.test_android.ui.composables.UserAvatar
import com.example.test_android.ui.theme.GrayColor
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.SecondaryColor
import com.example.test_android.ui.theme.DangerColor




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        // 👇 Your modal content here
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Are you sure you want to unfriend Ugochukwu?", textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ){

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
                    Text("Unfriend")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, Id: Int?) {


    val scrollState = rememberScrollState()

    var url =
        "https://imgs.search.brave.com/cxK8yYoMfgHkWGAMV5994P1TJD_KjjJr1flcmxioz00/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pMC53/cC5jb20vcGljanVt/Ym8uY29tL3dwLWNv/bnRlbnQvdXBsb2Fk/cy93b21hbi13aXRo/LXN1bi1nbGFzc2Vz/LWluLWZsb3dlci1m/aWVsZC1zdW1tZXIt/ZnJlZS1waG90by5q/cGc_dz02MDAmcXVh/bGl0eT04MA"
    var url2 =
        "https://imgs.search.brave.com/9-JGj1b5kManUsTsS_a26drmpTalcF9tGnPqjPNcMM4/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tZWRp/YS5nZXR0eWltYWdl/cy5jb20vaWQvOTU0/NDIyNjUvcGhvdG8v/bG90dGVyeS5qcGc_/cz02MTJ4NjEyJnc9/MCZrPTIwJmM9b2Zs/S000TUNwQTBReFZl/Zkd4Uml5UUxhUkpT/YWhNT2NmYmxJZ2dR/ZTlDbz0"

    var isNotificationChecked by remember { mutableStateOf(false) }
    var isMessageChecked by remember { mutableStateOf(false) }


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
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = PrimaryColor

                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(40.dp)
                            .background(SecondaryColor)
                            .border(1.dp, PrimaryColor, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        onClick = { navController.navigate("chat-detail/$Id") },


                        ) {

                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "More",
                            tint = PrimaryColor,
                            modifier = Modifier.size(20.dp)

                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .size(40.dp)
                            .background(SecondaryColor)
                            .border(1.dp, PrimaryColor, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        onClick = { },

                        ) {

                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "More",
                            tint = PrimaryColor,
                            modifier = Modifier.size(20.dp)

                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .size(40.dp)
                            .background(SecondaryColor)
                            .border(1.dp, PrimaryColor, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp)),
                        onClick = { },

                        ) {

                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = PrimaryColor,
                            modifier = Modifier.size(20.dp)

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
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray)
                )

                Spacer(modifier = Modifier.size(8.dp))

                UserAvatar(
                    photoUrl = url,
                    modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Ugochukwu",
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = PoppinsFont,
                    fontSize = 18.sp,
                )
                Text(
                    text = "+92-857-5374-4789",
                    fontWeight = FontWeight.Bold,
                    fontFamily = PoppinsFont,
                    fontSize = 12.sp,
                )

                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text(
                        text = "Can’t Talk Chat only",
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFont,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "February 2, 2022",
                        fontFamily = PoppinsFont,
                        fontSize = 12.sp
                    )
                }

                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray)
                )


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //child row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = PrimaryColor,
                                modifier = Modifier.size(22.dp)

                            )

                            Text(
                                text = "Mute Notifications",
                                fontWeight = FontWeight.Bold,
                                fontFamily = PoppinsFont,
                                fontSize = 14.sp
                            )
                        }

                        Switch(
                            checked = isNotificationChecked,
                            onCheckedChange = { isNotificationChecked = it },
                            modifier = Modifier.scale(0.7f), // shrink size (0.8 = 80%)
                            colors = SwitchDefaults.colors(
                                uncheckedThumbColor = Color.White,      // OFF thumb
                                uncheckedTrackColor = GrayColor,      // OFF track
                                uncheckedBorderColor = GrayColor
                            )
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //child row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = "clock",
                                tint = PrimaryColor,
                                modifier = Modifier.size(22.dp)

                            )

                            Text(
                                text = "Delete messages after 1 Day",
                                fontWeight = FontWeight.Bold,
                                fontFamily = PoppinsFont,
                                fontSize = 14.sp
                            )
                        }

                        Switch(
                            checked = isMessageChecked,
                            onCheckedChange = { isMessageChecked = it },
                            modifier = Modifier.scale(0.7f), // shrink size (0.8 = 80%)
                            colors = SwitchDefaults.colors(
                                uncheckedThumbColor = Color.White,      // OFF thumb
                                uncheckedTrackColor = GrayColor,      // OFF track
                                uncheckedBorderColor = GrayColor
                            )
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //child row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "clock",
                                tint = PrimaryColor,
                                modifier = Modifier.size(22.dp)

                            )

                            Text(
                                text = "Starred Messages",
                                fontWeight = FontWeight.Bold,
                                fontFamily = PoppinsFont,
                                fontSize = 14.sp
                            )
                        }

                        Text(
                            text = "6",
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsFont,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 12.dp)
                        )
                    }
                }

                // divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray)
                )


            }
            // common groups
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Text(
                    text = "Groups In Common",
                    fontFamily = PoppinsFont,
                    fontSize = 12.sp
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        // Apply the clickable modifier to the card
                        .clickable(onClick = { navController.navigate("chat-detail/$Id") }),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )


                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            model = url2, // Reference your image here
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentDescription = "user image",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),

                                ) {

                                Text(
                                    text = "Friend Forever",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = PoppinsFont,
                                    fontSize = 14.sp,

                                    )
                                Text(
                                    text = "You, Ali Tahir, Sharjeel, and 2 Others",
                                    fontFamily = PoppinsFont,
                                    fontSize = 12.sp,

                                    )

                            }
                        }

                    }
                }
            }

            // divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.clickable { showBottomSheet = true }) {
                    Row(

                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Filled.Block,
                            contentDescription = "block",
                            tint = DangerColor,
                            modifier = Modifier.size(22.dp)

                        )

                        Text(
                            text = "Unfriend Crush",
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsFont,
                            fontSize = 14.sp,
                            color = DangerColor
                        )
                    }
                }

                Box(modifier = Modifier.clickable { print("clicked me") }) {
                    Row(

                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Filled.ThumbDownOffAlt,
                            contentDescription = "thumb",
                            tint = DangerColor,
                            modifier = Modifier.size(22.dp)

                        )

                        Text(
                            text = "Report Crush",
                            fontWeight = FontWeight.Bold,
                            fontFamily = PoppinsFont,
                            fontSize = 14.sp,
                            color = DangerColor
                        )
                    }
                }


            }

        }

    }

    // Show modal when needed
    if (showBottomSheet) {
        MyModalBottomSheet(
            sheetState = sheetState,
            onDismiss = { showBottomSheet = false }
        )
    }
}











