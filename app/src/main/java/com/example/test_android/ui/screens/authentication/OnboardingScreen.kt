package com.example.test_android.ui.screens.authentication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.test_android.R
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.PoppinsFont




@Composable
fun OnboardingScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier.size(100.dp).padding(bottom=30.dp),
            painter = painterResource(id = R.drawable.chatify), // Reference your image here
            contentDescription = "chatify icon"
        )
        Column(

            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 32.dp, horizontal=44.dp ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Text(
                text = "Welcome to Chatify",
                fontWeight = FontWeight.Bold,
                fontFamily = PoppinsFont,
                fontSize = 24.sp
            )

            Text(
                text = "Read our Privacy Policy. Tap “Agree and Continue” to accept Terms of service",
                fontSize = 12.sp,
                fontFamily = PoppinsFont,
                textAlign = TextAlign.Center

            )

            Button(
                modifier = Modifier.width(500.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,  // Sets the button's background color
                    contentColor = Color.White   // Sets the color of the text/icon inside
                ),
                onClick = {
                    // This is the action that happens when the button is clicked
//                    println("Button clicked!")
                    navController.navigate("login")
                }) {
                Text("Agree and Continue") // This is the text displayed on the button
            }
        }
    }
}
