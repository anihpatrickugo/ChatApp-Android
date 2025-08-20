package com.example.test_android.ui.screens.authentication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.test_android.R
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.PoppinsFont
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.test_android.ui.theme.GrayColor


import androidx.compose.material3.ExperimentalMaterial3Api // 1. Add this import


@OptIn(ExperimentalMaterial3Api::class) // 3. Add this annotation to the Composable
@Composable
fun SignupScreen(navController: NavController) {


    val scrollState = rememberScrollState()

    var first_name by remember { mutableStateOf("") }
    var last_name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier.padding(bottom=30.dp),
            painter = painterResource(id = R.drawable.chatify), // Reference your image here
            contentDescription = "chatify icon"
        )
        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Text(
                text = "Sign up for free",
                fontWeight = FontWeight.Bold,
                fontFamily = PoppinsFont,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Column(modifier = Modifier.padding(vertical=16.dp)) {

                Text(text = "Lastname:", Modifier.padding(horizontal = 20.dp))
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = first_name,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        first_name = newText
                    },

                    label = { Text("Enter your first name", color = Color.LightGray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "user icon"
                        )
                    },


                    )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(text = "Lastname:", Modifier.padding(horizontal = 20.dp))
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = last_name,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        last_name = newText
                    },

                    label = { Text("Enter your last name", color = Color.LightGray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "user icon"
                        )
                    },


                    )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(text = "Username:", Modifier.padding(horizontal = 20.dp))
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = username,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        username = newText
                    },

                    label = { Text("Enter your username", color = Color.LightGray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "User icon"
                        )
                    },


                    )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(text = "Email:", Modifier.padding(horizontal = 20.dp))
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = email,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        email = newText
                    },

                    label = { Text("Enter your email", color = Color.LightGray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Mail,
                            contentDescription = "Email icon"
                        )
                    },


                    )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(text = "Passowrd:", Modifier.padding(horizontal = 20.dp))
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = password,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        password = newText
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text("Enter your password", color = Color.LightGray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp),
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Lock icon"
                        )
                    },

                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    }


                )

            }


            Button(
                modifier = Modifier.width(500.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,  // Sets the button's background color
                    contentColor = Color.White   // Sets the color of the text/icon inside
                ),
                onClick = {
                    // This is the action that happens when the button is clicked
                    navController.navigate("home")
                }) {
                Text("sign up") // This is the text displayed on the button
            }

            Text(
                text = buildAnnotatedString {
                    // This part of the text will have the default style
                    append("Already have an account? ")

                    // This part of the text will be highlighted in PrimaryColor
                    withStyle(style = SpanStyle(
                        color = PrimaryColor, // Use your custom primary color
                        fontWeight = FontWeight.Bold // Optional: make it bold
                    )
                    ) {
                        append("Sign In")
                    }
                },
                modifier = Modifier.clickable {
                    // Navigate to the signup screen when the text is clicked
                    navController.navigate("login")
                }
            )
        }
    }
}
