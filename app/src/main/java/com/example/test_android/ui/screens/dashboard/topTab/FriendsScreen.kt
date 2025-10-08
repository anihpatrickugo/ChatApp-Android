package com.example.test_android.ui.screens.dashboard.topTab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.algolia.instantsearch.compose.searchbox.SearchBoxState
import com.algolia.search.model.response.ResponseSearch
import com.example.test_android.data.remote.model.SearchUser
import com.example.test_android.data.remote.model.User
import com.google.gson.Gson
import com.example.test_android.ui.composables.FriendListCard
import com.example.test_android.ui.providers.LocalUserViewModel
import com.example.test_android.ui.theme.GrayColor
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.SecondaryColor
import com.example.test_android.ui.viewmodel.UserState
import kotlinx.coroutines.launch


@Composable
fun SearchBox (
    modifier: Modifier = Modifier,
    searchBoxState: SearchBoxState = SearchBoxState(),
    onValueChange: (String) -> Unit = {}
){
    OutlinedTextField(
        value = searchBoxState.query,
        onValueChange = {
            searchBoxState.setText(it)
            onValueChange(it)
        },
        placeholder = { Text("Search Friend", fontSize = 12.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
        textStyle = TextStyle(fontSize = 14.sp),
        maxLines = 3,
//        trailingIcon = {
//            IconButton(
//                modifier = Modifier
//                    .size(36.dp)
//                    .padding(end = 8.dp)
//                    .background(PrimaryColor, CircleShape),
//                onClick = {}
//            ) {
//                Icon(imageVector = Icons.Filled.PersonSearch, contentDescription = "Search")
//            }
//        },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = SecondaryColor,
            disabledContainerColor = GrayColor,
            focusedIndicatorColor = PrimaryColor,
            unfocusedIndicatorColor = Color.Gray
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),

        keyboardActions = KeyboardActions(
            onSearch = { searchBoxState.setText(searchBoxState.query, true) }
        )
    )
}


@Composable
fun ProductsList(
    friendList : List<ResponseSearch.Hit>,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val userViewModel = LocalUserViewModel.current
    val userState by userViewModel.userState.collectAsState()
    val user = (userState as? UserState.Success)?.user

   if (friendList.isNotEmpty()){
       LazyColumn(
           modifier = modifier,
           verticalArrangement = Arrangement.spacedBy(8.dp),

           ) {
           items(friendList) { item ->

               val friend = Gson().fromJson(item.json.toString(), SearchUser::class.java)
               if(user?.username != friend.username){
                   FriendListCard(navController, user=friend)
               }

           }
       }
   } else {
       Column(
           modifier = Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text(text = "No friend to chat with", color = PrimaryColor)
       }
   }
    
}



@Composable
fun FriendsScreen(navController: NavController, friendList : List<ResponseSearch.Hit>,
                  searchBoxState: SearchBoxState) {



    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()




    Scaffold(
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
        topBar = {
            SearchBox(
                searchBoxState = searchBoxState,
                onValueChange = { scope.launch { listState.scrollToItem(0) } }
            )
        }
    ) { innerPadding ->
        ProductsList(
            friendList=friendList,
            navController=navController,
            modifier= Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}