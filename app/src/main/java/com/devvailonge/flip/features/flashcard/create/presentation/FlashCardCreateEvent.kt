package com.devvailonge.flip.features.flashcard.create.presentation

sealed class FlashCardCreateEvent {

    data class Insert(val textFront:String, val textBack:String,val categoryId: Long): FlashCardCreateEvent()
}