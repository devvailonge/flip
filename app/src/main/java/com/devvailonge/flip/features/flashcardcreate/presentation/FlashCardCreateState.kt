package com.devvailonge.flip.features.flashcardcreate.presentation

import androidx.annotation.StringRes

sealed class FlashCardCreateState {

    object Loading: FlashCardCreateState()
    data class Message(@StringRes val message: Int): FlashCardCreateState()

}