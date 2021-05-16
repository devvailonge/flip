package com.devvailonge.flip.features.flashcard.delete.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListState

class FlashCardDeleteUseCase(
    application: FlipApplication,
    private val flashCardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
) {

    fun perform(flashCardEntity: FlashCardEntity): LiveData<FlashCardListState> {

        return liveData {
            try {
                flashCardDao.deleteFlashCard(flashCardEntity)
                emit(
                    FlashCardListState.DeleteSuccess(
                        flashCardEntity.categoryId,
                        R.string.create_flashcard_success
                    )
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(FlashCardListState.Message(R.string.flashcard_error))
            }
        }
    }

    companion object {
        fun create(): FlashCardDeleteUseCase {
            return FlashCardDeleteUseCase(FlipApplication.instance)
        }
    }
}