package com.devvailonge.flip.features.flashcard.create.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateState
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity

class InsertFlashCardUseCase(
    application: Application,
    private val flashCardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
) {

    fun perform(
        textFront: String,
        textBack: String,
        categoryId: Long
    ): LiveData<FlashCardCreateState> {
        return liveData {
            emit(FlashCardCreateState.Loading)
            try {

                val trimTextFront = textFront.trim()
                val trimTextBack = textBack.trim()
                when {
                    trimTextFront.isEmpty() -> {
                        emit(FlashCardCreateState.Failed(R.string.create_flashcard_front_empty))
                    }
                    trimTextBack.isEmpty() -> {
                        emit(FlashCardCreateState.Failed(R.string.create_flashcard_back_empty))
                    }
                    else -> {
                        flashCardDao.addFlashCard(
                            FlashCardEntity(
                                frontText = trimTextFront,
                                backText = trimTextBack,
                                categoryId = categoryId
                            )
                        )
                        emit(
                            FlashCardCreateState.Success(
                                textFront,
                                R.string.create_flashcard_success
                            )
                        )
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(FlashCardCreateState.Failed(R.string.flashcard_error))
            }
        }
    }

    companion object {
        fun create(): InsertFlashCardUseCase {
            return InsertFlashCardUseCase(FlipApplication.instance)
        }
    }
}

