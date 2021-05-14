package com.devvailonge.flip.features.flashcard.list.presentation

import com.devvailonge.flip.features.flashcard.data.FlashCardEntity

sealed class FlashCardListEvent {

    data class Fetch(val categoryId: Long) : FlashCardListEvent()

    data class Delete(val flashcardEntity: FlashCardEntity): FlashCardListEvent()

}