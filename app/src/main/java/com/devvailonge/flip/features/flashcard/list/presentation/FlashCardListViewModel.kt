package com.devvailonge.flip.features.flashcard.list.presentation

import androidx.lifecycle.*
import com.devvailonge.flip.features.flashcard.list.domain.FetchFlashCardsUseCase

class FlashCardListViewModel(
    private val fetchFlashCardsUseCase: FetchFlashCardsUseCase
) : ViewModel() {

    private val event = MutableLiveData<FlashCardListEvent>()

    val state: LiveData<FlashCardListState> = event.switchMap {
        when(it) {
            is FlashCardListEvent.Fetch -> fetchFlashCardsUseCase.perform(it.categoryId)
        }
    }

    fun dispatch(event: FlashCardListEvent) {
        this.event.postValue(event)
    }

    class FlashCardListViewModelFactory constructor(
        private val fetchFlashCardsUseCase: FetchFlashCardsUseCase = FetchFlashCardsUseCase.create()
    ) :
            ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(FlashCardListViewModel::class.java)) {
                FlashCardListViewModel(
                    this.fetchFlashCardsUseCase
                ) as T
            }else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}