package com.devvailonge.flip.features.flashcard.create.presentation

import androidx.annotation.StringRes

sealed class FlashCardCreateState {

    object Loading: FlashCardCreateState()
    
    data class Failed(@StringRes val message: Int): FlashCardCreateState()

    data class Success(val frontText: String, @StringRes val message: Int): FlashCardCreateState()

}