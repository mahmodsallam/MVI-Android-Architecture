package com.example.framework.mvi.ui.main.viewstate

import com.example.framework.mvi.data.model.User


/**
 * it is a sealed class to map the states
 * of the UI like loading , Idle , get users list or an error
 * */
sealed class MainState {
    object Idle : MainState()  //Idle UI State
    object Loading : MainState() //Loading UI state
    data class Users(val user: List<User>) : MainState() //get a list of users
    data class Error(val error: String?) : MainState()   //getting an error
}