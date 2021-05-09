package com.devvailonge.flip.features.flashcard.list.presentation

sealed class FlashCardListEvent {

    data class Fetch(val categoryId: Long) : FlashCardListEvent()

}