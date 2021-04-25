package com.devvailonge.flip.features.flashcardcreate.presentation

import com.devvailonge.flip.features.categories.data.CategoryEntity

sealed class FlashCardCreateEvent {

    data class Insert(val textFront:String, val textBack:String,val categoryId: Int): FlashCardCreateEvent()
}