package com.devvailonge.flip.features.flashcard.delete.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity
import com.devvailonge.flip.features.flashcard.delete.presentation.FlashCardDeleteEvent
import com.devvailonge.flip.features.flashcard.delete.presentation.FlashCardDeleteState
import java.lang.Exception

class DeleteFlashCardUseCase(
    application: FlipApplication,
    private val flashCardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
) {

    fun perform(textFront:String, textBack:String, categoryId:Long):LiveData<FlashCardDeleteState>{

        return liveData {
            emit(FlashCardDeleteState.Loading)
            try {
                flashCardDao.deleteFlashCard(FlashCardEntity(frontText = textFront, backText = textBack, categoryId = categoryId))
                emit(FlashCardDeleteState.Message(R.string.create_flashcard_success))
            } catch (exception: Exception){
                emit(FlashCardDeleteState.Message(R.string.flashcard_error))
            }


        }
    }

    companion object{
        fun delete(): DeleteFlashCardUseCase{
            return DeleteFlashCardUseCase(FlipApplication.instance)
        }
    }
}