
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
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.R
import com.example.test_android.ui.composables.UserAvatar
import com.example.test_android.ui.providers.LocalUserViewModel
import com.example.test_android.ui.theme.DangerColor
import com.example.test_android.ui.theme.DangerLigtColor
import com.example.test_android.ui.theme.GrayColor
import com.example.test_android.ui.theme.SecondaryColor
import com.example.test_android.ui.viewmodel.UserState



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutModal(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onAccept: () -> Unit
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
            Text("Are you sure you want to logout?", textAlign = TextAlign.Center)
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
                    onClick = onAccept,
                    modifier = Modifier.width(120.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("yes, logout")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {



    val userViewModel = LocalUserViewModel.current
    val userState by userViewModel.userState.collectAsState()



    val scrollState = rememberScrollState()

    var url =
        "https://imgs.search.brave.com/cxK8yYoMfgHkWGAMV5994P1TJD_KjjJr1flcmxioz00/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pMC53/cC5jb20vcGljanVt/Ym8uY29tL3dwLWNv/bnRlbnQvdXBsb2Fk/cy93b21hbi13aXRo/LXN1bi1nbGFzc2Vz/LWluLWZsb3dlci1m/aWVsZC1zdW1tZXIt/ZnJlZS1waG90by5q/cGc_dz02MDAmcXVh/bGl0eT04MA"

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
                    text = "Settings",
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
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(GrayColor)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .clickable { navController.navigate("edit-profile") },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                UserAvatar(
                    photoUrl = (userState as? UserState.Success)?.user?.photo,
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {

                    when (userState) {
                        is UserState.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp))
                        }
                        is UserState.Success -> {
                            val user = (userState as UserState.Success).user
                            Text(
                                text = "${user.username}",
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = PoppinsFont
                            )
                            Text(
                                text = user.email,
                                fontFamily = PoppinsFont,
                                fontSize = 12.sp
                            )
                        }
                        is UserState.Error -> {
                            Text(
                                text = "Error loading user",
                                color = DangerColor,
                                fontSize = 12.sp
                            )
                        }
                        else -> {
                            Text("No user data", fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                IconButton(
                    modifier = Modifier
                        .size(40.dp),
                    onClick = { navController.navigate("qr-code") },

                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.qrcodeicon),
                        contentDescription = "More",
                        tint = PrimaryColor,
                        modifier = Modifier.size(20.dp)

                    )
                }
            }
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("account") },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    //icon and title

                    Row(
                       verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier
                                .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondaryColor),
                            onClick = { },

                            ) {

                            Icon(
                                imageVector = Icons.Default.PersonOutline,
                                contentDescription = "person",
                                tint= PrimaryColor

                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))

                        Text("Account", fontWeight = FontWeight.Bold,  fontSize = 12.sp, fontFamily = PoppinsFont)
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint= PrimaryColor
                    )

                }

                //2
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    //icon and title

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier
                                .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondaryColor),
                            onClick = { },

                            ) {

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Chat,
                                contentDescription = "person",
                                tint= PrimaryColor

                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))

                        Text("Chats", fontWeight = FontWeight.Bold,  fontSize = 12.sp, fontFamily = PoppinsFont)
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint= PrimaryColor
                    )

                }

                //3
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    //icon and title

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier
                                .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondaryColor),
                            onClick = { },

                            ) {

                            Icon(
                                imageVector = Icons.Default.NotificationsNone,
                                contentDescription = "notif",
                                tint= PrimaryColor

                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))

                        Text("Notifications", fontWeight = FontWeight.Bold,  fontSize = 12.sp, fontFamily = PoppinsFont)
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint= PrimaryColor
                    )

                }


                //4
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    //icon and title

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier
                                .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondaryColor),
                            onClick = { },

                            ) {

                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = "security",
                                tint= PrimaryColor

                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))

                        Text("Security", fontWeight = FontWeight.Bold,  fontSize = 12.sp, fontFamily = PoppinsFont)
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint= PrimaryColor
                    )

                }


                //5
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("help") },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    //icon and title

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier
                                .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondaryColor),
                            onClick = { },

                            ) {

                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "info",
                                tint= PrimaryColor

                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))

                        Text("Help", fontWeight = FontWeight.Bold,  fontSize = 12.sp, fontFamily = PoppinsFont)
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "arrow",
                        tint= PrimaryColor
                    )

                }


                //6
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showBottomSheet = true },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    //icon and title

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier
                                .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .clip(RoundedCornerShape(12.dp))
                                .background(DangerLigtColor),
                            onClick = { },

                            ) {

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Logout,
                                contentDescription = "logout",
                                tint= DangerColor

                            )
                        }

                        Spacer(modifier = Modifier.size(12.dp))

                        Text("Logout", fontWeight = FontWeight.Bold,  fontSize = 12.sp, fontFamily = PoppinsFont)
                    }


                }



            }
        }

    }

    // Show modal when needed
    if (showBottomSheet) {
        LogoutModal(
            sheetState = sheetState,
            onDismiss = {showBottomSheet = false },
            onAccept = { userViewModel.logout {
                showBottomSheet = false
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true } // clear backstack
                }
            }}
        )
    }

}











