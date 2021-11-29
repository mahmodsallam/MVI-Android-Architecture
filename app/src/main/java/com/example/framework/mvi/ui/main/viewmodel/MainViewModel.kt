package com.example.framework.mvi.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framework.mvi.data.repository.MainRepository
import com.example.framework.mvi.ui.main.intent.MainIntent
import com.example.framework.mvi.ui.main.viewstate.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: MainRepository
) : ViewModel() {


    //linked list of intents
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    //The state and getting it
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> // uses state flow
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                    is MainIntent.AnyAction -> "Do something"
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Users(repository.getUsers())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}