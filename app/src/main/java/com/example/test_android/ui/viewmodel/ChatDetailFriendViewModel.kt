package com.example.test_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatDetailFriendViewModel : ViewModel() {
    private val _friend = MutableStateFlow<UserState>(UserState.Loading)
    val friend: StateFlow<UserState> = _friend

    fun fetchFriendInRoom(roomId: Int) {
        viewModelScope.launch {
            try {
                _friend.value = UserState.Loading
                val friendUser = ApiClient.chatApi.getFriendInRoom(roomId)
                _friend.value = UserState.Success(friendUser)
            } catch (e: Exception) {
                _friend.value = UserState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
