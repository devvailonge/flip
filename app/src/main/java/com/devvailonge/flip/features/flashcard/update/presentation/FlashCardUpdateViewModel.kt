package com.devvailonge.flip.features.flashcard.update.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateState
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.update.domain.UpdateFlashCardUseCase

class FlashCardUpdateViewModel (
    private val updateFlashCardUseCase: UpdateFlashCardUseCase
) : ViewModel() {

    private val event = MutableLiveData<FlashCardUpdateEvent>()

    val state: LiveData<FlashCardUpdateState> = event.switchMap {
        when(it){
            is FlashCardUpdateEvent.Update -> updateFlashCardUseCase.perform(it.textFront, it.textBack, it.categoryId)
        }
    }

    fun dispatch (event: FlashCardUpdateEvent){
        this.event.postValue(event)
    }

}