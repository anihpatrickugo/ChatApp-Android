package com.example.test_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.test_android.ui.theme.TestandroidTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.ui.providers.LocalUserViewModel
import com.example.test_android.ui.screens.authentication.OnboardingScreen
import com.example.test_android.ui.screens.authentication.LoginScreen
import com.example.test_android.ui.screens.authentication.SignupScreen
import com.example.test_android.ui.screens.dashboard.AccountScreen
import com.example.test_android.ui.screens.dashboard.ChatDetailScreen
import com.example.test_android.ui.screens.dashboard.GroupChatDetailScreen
import com.example.test_android.ui.screens.dashboard.topTab.TopTabsScreen
import com.example.test_android.ui.screens.dashboard.ProfileScreen
import com.example.test_android.ui.screens.dashboard.GroupInfoScreen
import com.example.test_android.ui.screens.dashboard.SettingsScreen
import com.example.test_android.ui.screens.dashboard.EditProfileScreen
import com.example.test_android.ui.screens.dashboard.HelpScreen
import com.example.test_android.ui.screens.dashboard.QRCodeTab.QRCodeTab
import com.example.test_android.ui.viewmodel.ChatViewModel
import com.example.test_android.ui.viewmodel.UserViewModel
import com.example.test_android.ui.viewmodel.UserViewModelFactory


class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(this)
    }

    private val chatViewModel: ChatViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Tell system to let Compose handle keyboard insets
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            TestandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(red=255,green=255, blue=255),

                    ) {
                      MyAppNavigation(userViewModel, chatViewModel)
                }
            }
        }
    }
}

@Composable
fun MyAppNavigation(userViewModel: UserViewModel, chatViewModel: ChatViewModel) {

    // Fetch user info, then connect WebSocket once authenticated
    LaunchedEffect(Unit) {
        val user = userViewModel.getUserInfo() // suspend function to fetch user
        if (user != null) {
            chatViewModel.connect() // connect only after user is authenticated
        }
    }


    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalUserViewModel provides userViewModel
    ) {

        NavHost(
            navController = navController,
            startDestination = MyApp.startDestination
        ) {
            composable("onboarding") {
                OnboardingScreen(navController = navController)
            }
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("signup") {
                SignupScreen(navController = navController)
            }
            composable("home") {
                TopTabsScreen(navController = navController)
            }

            composable(
                route = "chat-detail/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getInt("itemId")
                ChatDetailScreen(navController=navController, itemId = itemId, chatViewModel = chatViewModel)
            }

            composable(
                route = "group-chat-detail/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getInt("itemId")
                GroupChatDetailScreen(navController=navController, itemId = itemId)
            }


            composable(
                route = "profile/{Id}",
                arguments = listOf(navArgument("Id") { type = NavType.IntType })
            ) { backStackEntry ->
                val Id = backStackEntry.arguments?.getInt("Id")
                ProfileScreen(navController=navController, Id = Id)
            }

            composable(
                route = "group/{Id}",
                arguments = listOf(navArgument("Id") { type = NavType.IntType })
            ) { backStackEntry ->
                val Id = backStackEntry.arguments?.getInt("Id")
                GroupInfoScreen(navController=navController, Id = Id)
            }

            composable("settings") {
                SettingsScreen(navController = navController)
            }

            composable("edit-profile") {
                EditProfileScreen(navController = navController)
            }

            composable ("qr-code") {
                QRCodeTab(navController = navController)
            }

            composable ("account") {
                AccountScreen(navController)
            }

            composable ("help") {
                HelpScreen(navController)
            }
        }

    }
}