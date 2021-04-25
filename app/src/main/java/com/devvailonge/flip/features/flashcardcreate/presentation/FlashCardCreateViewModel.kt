package com.devvailonge.flip.features.flashcardcreate.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.devvailonge.flip.features.flashcardcreate.domain.InsertFlashCardUseCase

class FlashCardCreateViewModel (
    private val insertFlashCardUseCase: InsertFlashCardUseCase
) : ViewModel() {

    private val event = MutableLiveData<FlashCardCreateEvent>()

    val state: LiveData<FlashCardCreateState> = event.switchMap {
        when(it){
            is FlashCardCreateEvent.Insert -> insertFlashCardUseCase.perfom(it.textFront, it.textBack, it.categoryId)
        }
    }

    fun dispatch(event: FlashCardCreateEvent){
        this.event.postValue(event)
    }




}