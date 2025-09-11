package com.example.test_android.ui.screens.authentication

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.test_android.R
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.PoppinsFont
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.test_android.ui.theme.GrayColor


import androidx.compose.material3.ExperimentalMaterial3Api // 1. Add this import
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class) // 3. Add this annotation to the Composable
@Composable
fun SignupScreen(navController: NavController, context: Context = LocalContext.current) {


    val viewModel: SignUpViewModel = viewModel()
    val signUpState by viewModel.signUpState.collectAsStateWithLifecycle()


    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }

    var passwordVisible1 by remember { mutableStateOf(false) }
    var passwordVisible2 by remember { mutableStateOf(false) }



    LaunchedEffect(signUpState) {
        when (signUpState) {
            is SignUpState.Success -> {
                Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                navController.navigate("login") {
                    popUpTo("signup") { inclusive = true }
                }
            }
            is SignUpState.Error -> {
                Toast.makeText(context, "Signup Failed: ${(signUpState as SignUpState.Error).message}",
                    Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 34.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                modifier = Modifier.size(100.dp).padding(bottom=30.dp),
                painter = painterResource(id = R.drawable.chatify), // Reference your image here
                contentDescription = "chatify icon"
            )
            Text(
                text = "Sign up for free",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = PoppinsFont,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

        }


        item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Firstname:", Modifier.padding(horizontal = 20.dp),
                        fontWeight = FontWeight.Bold)

                    // 2. Pass the state variable to the value parameter
                    TextField(
                        value = firstName,
                        onValueChange = { newText: String ->
                            // 3. Update the state variable with the new text
                            firstName = newText
                        },

                        label = { Text("Enter your first name", color = Color.Gray, fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
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

                }
        }


        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Lastname:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = lastName,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        lastName = newText
                    },

                    label = { Text("Enter your last name", color = Color.Gray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
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
            }

        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
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
                        unfocusedIndicatorColor = Color.Transparent
                    ),


                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "User icon"
                        )
                    },
                )
            }

        }


        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Email:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = email,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        email = newText
                    },

                    label = { Text("Enter your email", color = Color.Gray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
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
            }

        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Passowrd:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = password1,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        password1 = newText
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text("Enter your password", color = Color.Gray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    singleLine = true,
                    visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
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
                        val image = if (passwordVisible1)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (passwordVisible1) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
                            Icon(imageVector = image, description)
                        }
                    }


                )
            }
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Confirm Passowrd:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = password2,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        password2= newText
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text("Confirm your password", color = Color.Gray, fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    singleLine = true,
                    visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
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
                        val image = if (passwordVisible2)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (passwordVisible2) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible2 = !passwordVisible2 }) {
                            Icon(imageVector = image, description)
                        }
                    }

                )

            }
        }


        item{
            Button(
                modifier = Modifier.width(500.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,  // Sets the button's background color
                    contentColor = Color.White   // Sets the color of the text/icon inside
                ),
                onClick = {
                    viewModel.signUp(username, email, password1, password2, firstName, lastName)
                }) {

                when (val state = signUpState) {
                    is SignUpState.Idle -> Text("sign up") // This is the text displayed on the button
                    is SignUpState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp)
                    else -> Text("sign up")
                }
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
