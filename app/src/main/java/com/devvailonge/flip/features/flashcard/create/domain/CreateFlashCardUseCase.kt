package com.devvailonge.flip.features.flashcard.create.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateState
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity

class InsertFlashCardUseCase(
    application: Application,
    private val flashCardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
    ) {

    fun perfom(textFront:String, textBack:String, categoryId:Long) : LiveData<FlashCardCreateState>{
        return liveData {
            emit(FlashCardCreateState.Loading)
            try {
                flashCardDao.addFlashCard(FlashCardEntity(frontText = textFront, backText = textBack, categoryId = categoryId))
                emit(FlashCardCreateState.Message(R.string.create_flashcard_success))
            } catch (exception:Exception) {
                emit(FlashCardCreateState.Message(R.string.flashcard_error))
            }

        }
    }

    companion object{
        fun create(): InsertFlashCardUseCase{
            return InsertFlashCardUseCase(FlipApplication.instance)
        }
    }

}

