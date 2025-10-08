package com.example.test_android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.remote.api.ApiClient
import com.example.test_android.data.remote.model.FriendRequestDto
import com.example.test_android.data.remote.model.FriendResquestResponse
import com.example.test_android.data.remote.model.SearchUser
import com.example.test_android.data.remote.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


sealed class RequestState {
    object Idle : RequestState()
    object Loading : RequestState()
    data class Success(val response: FriendResquestResponse) : RequestState()
    data class Error(val message: String) : RequestState()
}



sealed class FriendUIEvent {
    data class ShowToast(val message: String) : FriendUIEvent()
    object NavigateBack : FriendUIEvent()
}

class FriendRequestViewModel : ViewModel() {

    private val _requestList = MutableStateFlow<List<FriendRequestDto>>(emptyList())
    val requestList: StateFlow<List<FriendRequestDto>> = _requestList

    private val _requestState = MutableStateFlow<RequestState>(RequestState.Idle)
    val requestState: StateFlow<RequestState> = _requestState

    // 👇 One-time UI events
    private val _uiEvent = Channel<FriendUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun listFriendRequest(){
        viewModelScope.launch {
            try {
                val list = ApiClient.friendRequestService.ListFriendRequest()
                _requestList.value = list

            } catch (e: Exception){
                _requestState.value = RequestState.Error("Error: ${e.message}")
                _uiEvent.send(FriendUIEvent.ShowToast("Failed to fetch friend request list"))
            }
        }
    }

    fun sendFriendRequest(id: Int) {
        viewModelScope.launch {
            _requestState.value = RequestState.Loading
            try {
                val response = ApiClient.friendRequestService.sendFriendRequest(id)

                _requestState.value = RequestState.Success(response)
                _uiEvent.send(FriendUIEvent.ShowToast("Friend request sent!"))
            } catch (e: Exception) {
                _requestState.value = RequestState.Error("Error: ${e.message}")
                _uiEvent.send(FriendUIEvent.ShowToast("Failed to send request"))
            }
        }
    }


    fun declineFriendRequest(id: Int) {
        viewModelScope.launch {
            _requestState.value = RequestState.Loading
            try {
                val response = ApiClient.friendRequestService.declineFriendRequest(id)

                _requestState.value = RequestState.Success(response)
                _uiEvent.send(FriendUIEvent.ShowToast("Friend request declined!"))
                _uiEvent.send(FriendUIEvent.NavigateBack)
            } catch (e: Exception) {
                _requestState.value = RequestState.Error("Error: ${e.message}")
                _uiEvent.send(FriendUIEvent.ShowToast("Failed to decline request"))
            }
        }
    }


    fun acceptFriendRequest(id: Int) {
        viewModelScope.launch {
            _requestState.value = RequestState.Loading
            try {
                val response = ApiClient.friendRequestService.acceptFriendRequest(id)

                _requestState.value = RequestState.Success(response)
                _uiEvent.send(FriendUIEvent.ShowToast("Friend request accepted!"))
                _uiEvent.send(FriendUIEvent.NavigateBack)
            } catch (e: Exception) {
                _requestState.value = RequestState.Error("Error: ${e.message}")
                _uiEvent.send(FriendUIEvent.ShowToast("Failed to accept request"))
            }
        }
    }
}
