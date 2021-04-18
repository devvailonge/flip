package com.devvailonge.flip.features.flashcard.presentation

import androidx.lifecycle.*
import com.devvailonge.flip.features.flashcard.domain.FetchFlashCardsUseCase
import java.lang.IllegalArgumentException

class FlashCardListViewModel(
    private val fetchFlashCardsUseCase: FetchFlashCardsUseCase
) : ViewModel() {

    private val event = MutableLiveData<FlashCardListEvent>()

    val state: LiveData<FlashCardListState> = event.switchMap {
        when(it) {
            FlashCardListEvent.Fetch -> fetchFlashCardsUseCase.perform()
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