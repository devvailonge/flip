package com.devvailonge.flip.features.flashcard.update.presentation

sealed class FlashCardUpdateEvent {

    data class Update(val textFront:String, val textBack:String, val categoryId: Long) : FlashCardUpdateEvent()
}