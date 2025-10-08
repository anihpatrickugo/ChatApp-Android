package com.example.test_android.ui.viewmodel


// ui/viewmodel/RoomViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.model.PrivateChatRoomDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PrivatChatListViewModel : ViewModel() {
    private val _privateRooms = MutableStateFlow<List<PrivateChatRoomDto>>(emptyList())
    val chatList: StateFlow<List<PrivateChatRoomDto>> = _privateRooms

    fun fetchPrivateRooms() {
        viewModelScope.launch {
            try {
                val rooms = ApiClient.chatApi.getPrivateRooms()
                _privateRooms.value = rooms
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
