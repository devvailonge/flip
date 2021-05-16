package com.devvailonge.flip.features.flashcard.list.presentation

import androidx.annotation.StringRes
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity

sealed class FlashCardListState{
    data class FlashCardList(val list: List<FlashCardEntity>) : FlashCardListState()
    data class Message(@StringRes val message : Int) : FlashCardListState()
    data class DeleteSuccess(val categoryId: Long, @StringRes val message : Int) : FlashCardListState()
    data class Loading(val isLoading : Boolean) : FlashCardListState()
    object Empty : FlashCardListState()

}

