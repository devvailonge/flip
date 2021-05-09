package com.devvailonge.flip.features.flashcard.delete.presentation

import androidx.annotation.StringRes

sealed class FlashCardDeleteState {

    object  Loading: FlashCardDeleteState()
    data class Message(@StringRes val message: Int): FlashCardDeleteState()
}