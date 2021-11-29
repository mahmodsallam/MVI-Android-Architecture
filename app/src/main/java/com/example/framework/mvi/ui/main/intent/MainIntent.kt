package com.example.framework.mvi.ui.main.intent

/**
 * Mapping the action that will happen to the UI
 * as an intent and make an object for it
 * Simply for UI Action simulation
 * */
sealed class MainIntent {
    object FetchUser : MainIntent()
    object AnyAction : MainIntent()
}