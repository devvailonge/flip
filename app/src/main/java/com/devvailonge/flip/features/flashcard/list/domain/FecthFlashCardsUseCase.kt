package com.devvailonge.flip.features.flashcard.list.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListState

class FetchFlashCardsUseCase(
    application: Application,
    private val flashcardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
) {

    fun perform(): LiveData<FlashCardListState> {
        return liveData {
            try {
                emit(FlashCardListState.Loading(isLoading = true))
                val source = flashcardDao
                    .getAllFlashCards()
                    .switchMap { list ->
                        liveData {
                            val state = if (list.isEmpty()) {
                                FlashCardListState.Empty
                            } else {
                                FlashCardListState.FlashCardList(list)
                            }
                            emit(state)
                            emit(FlashCardListState.Loading(isLoading = false))
                        }
                    }

                emitSource(source)
            } catch (exc: Exception) {
                emit(FlashCardListState.Loading(isLoading = false))
                emit(FlashCardListState.ErrorMessage(R.string.flashcard_error))
            }
        }
    }

    companion object {
        fun create(): FetchFlashCardsUseCase {
            return FetchFlashCardsUseCase(FlipApplication.instance)
        }
    }
}

