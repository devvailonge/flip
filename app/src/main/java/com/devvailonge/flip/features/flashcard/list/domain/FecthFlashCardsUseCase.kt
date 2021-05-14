package com.devvailonge.flip.features.flashcard.list.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListState

class FetchFlashCardsUseCase(
    application: Application,
    private val flashcardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
) {

    fun perform(categoryId: Long): LiveData<FlashCardListState> {
        return liveData {
            try {
                emit(FlashCardListState.Loading(isLoading = true))
                val result = flashcardDao
                    .getFlashcardByCategory(categoryId)

                val state = if (result.isEmpty()) {
                    FlashCardListState.Empty
                } else {
                    FlashCardListState.FlashCardList(result)
                }
                emit(state)
                emit(FlashCardListState.Loading(isLoading = false))

            } catch (exc: Exception) {
                emit(FlashCardListState.Loading(isLoading = false))
                emit(FlashCardListState.Message(R.string.flashcard_error))
            }
        }
    }

    companion object {
        fun create(): FetchFlashCardsUseCase {
            return FetchFlashCardsUseCase(FlipApplication.instance)
        }
    }
}

