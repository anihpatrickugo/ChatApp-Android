package com.example.test_android.ui.screens.authentication

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.test_android.R
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.PoppinsFont

//import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api // 1. Add this import
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test_android.ui.theme.DangerColor
import com.example.test_android.ui.theme.GrayColor


@OptIn(ExperimentalMaterial3Api::class) // 3. Add this annotation to the Composable
@Composable
fun LoginScreen(
    navController: NavController,//            firstName,
//            lastName
    context: Context = LocalContext.current
) {

    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(context)
    )

    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }


    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
            is LoginState.Error -> {
                Toast.makeText(context, "Login Failed: ${(loginState as LoginState.Error).message}",
                    Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier.size(100.dp).padding(bottom=30.dp),
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
                text = "Sign in to your Account",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = PoppinsFont,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )


            Column(modifier = Modifier.padding(vertical=16.dp)) {
                Text(text = "Username:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = username,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        username = newText
                    },

                    label = { Text("Enter your username", color = Color.Gray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Mail,
                            contentDescription = "Email icon"
                        )
                    },


                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(text = "Passowrd:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = password,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        password = newText
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text("Enter your password", color = Color.Gray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
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
                    viewModel.login(username, password)
                }) {

                when (val state = loginState) {
                    is LoginState.Idle -> Text("sign in") // This is the text displayed on the button
                    is LoginState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp)
                    else -> Text("sign in")
                }
            }
            Text(
                text = buildAnnotatedString {
                    // This part of the text will have the default style
                    append("Don't have an account? ")

                    // This part of the text will be highlighted in PrimaryColor
                    withStyle(style = SpanStyle(
                        color = PrimaryColor, // Use your custom primary color
                        fontWeight = FontWeight.Bold // Optional: make it bold
                    )) {
                        append("Sign Up")
                    }
                },
                modifier = Modifier.clickable {
                    // Navigate to the signup screen when the text is clicked
                    navController.navigate("signup")
                }
            )


        }
    }
}
