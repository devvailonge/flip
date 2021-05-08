package com.devvailonge.flip.features.flashcard.update.presentation

import androidx.annotation.StringRes

sealed class FlashCardUpdateState {

    object Loading: FlashCardUpdateState()
    data class Message(@StringRes val message : Int) : FlashCardUpdateState()
}