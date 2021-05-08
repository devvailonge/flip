package com.devvailonge.flip.features.flashcard.update.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity
import com.devvailonge.flip.features.flashcard.update.presentation.FlashCardUpdateState
import java.lang.Exception

class UpdateFlashCardUseCase(
    application: Application,
    private val flashCardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
) {

    fun perform(textFront:String, textBack:String, categoryId:Long) : LiveData<FlashCardUpdateState>{
        return liveData {
            emit(FlashCardUpdateState.Loading)
            try {
                flashCardDao.updateFlashCard(FlashCardEntity( frontText = textFront, backText = textBack, categoryId = categoryId))
                emit(FlashCardUpdateState.Message(R.string.create_category_success))
            } catch (exception:Exception) {
                emit(FlashCardUpdateState.Message(R.string.category_error_message))
            }
        }
    }

    companion object {
        fun create(): UpdateFlashCardUseCase{
            return UpdateFlashCardUseCase(FlipApplication.instance)
        }

    }
}