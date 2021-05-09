package com.devvailonge.flip.features.flashcard.create.presentation

import androidx.lifecycle.*
import com.devvailonge.flip.features.flashcard.create.domain.InsertFlashCardUseCase

class FlashCardCreateViewModel (
    private val insertFlashCardUseCase: InsertFlashCardUseCase
) : ViewModel() {

    private val event = MutableLiveData<FlashCardCreateEvent>()

    val state: LiveData<FlashCardCreateState> = event.switchMap {
        when(it){
            is FlashCardCreateEvent.Insert -> insertFlashCardUseCase.perform(it.textFront, it.textBack, it.categoryId)
        }
    }

    fun dispatch(event: FlashCardCreateEvent){
        this.event.postValue(event)
    }

    class FlashCardCreateViewModelFactory constructor(
        private val insertFlashCardUseCase: InsertFlashCardUseCase = InsertFlashCardUseCase.create()
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(FlashCardCreateViewModel::class.java)) {
                FlashCardCreateViewModel(
                    this.insertFlashCardUseCase
                ) as T
            }else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }


}