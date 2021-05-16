package com.devvailonge.flip.features.categories.delete

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.categories.data.CategoryDao
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListState

class CategoryDeleteUseCase(
    application: Application,
    private val categoryDao: CategoryDao = AppDataBase.getDataBase(application).categoryDao()
) {

    fun perform(categoryId: Long): LiveData<FlashCardListState> {
        return liveData {
            try {
                categoryDao
                    .deleteCategoryById(categoryId)

                emit(FlashCardListState.DeleteCategorySuccess(R.string.delete_category_success))
            } catch (exc: Exception) {
                exc.printStackTrace()
                emit(FlashCardListState.Loading(isLoading = false))
                emit(FlashCardListState.Message(R.string.delete_category_error))
            }
        }
    }

    companion object {
        fun create(): CategoryDeleteUseCase {
            return CategoryDeleteUseCase(FlipApplication.instance)
        }
    }
}

