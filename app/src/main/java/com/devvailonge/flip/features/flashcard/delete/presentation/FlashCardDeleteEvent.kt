package com.devvailonge.flip.features.flashcard.delete.presentation

sealed class FlashCardDeleteEvent {

    data class Delete(val textFront:String, val textBack:String, val categoryId: Long): FlashCardDeleteEvent()
}