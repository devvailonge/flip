package com.devvailonge.flip.features.flashcardcreate.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcardcreate.presentation.FlashCardCreateState

class InsertFlashCardUseCase(
    application: Application,
    private val flashCardDao: FlashCardDao = AppDataBase.getDataBase(application).flashcardDao()
    ) {

    fun perfom(textFront:String, textBack:String, categoryId:Int) : LiveData<FlashCardCreateState>{
        TODO()//return liveData {
            //flashCardDao.addFlashCard()
        //}
    }

    companion object{
        fun create(): InsertFlashCardUseCase{
            return InsertFlashCardUseCase(FlipApplication.instance)
        }
    }

}

