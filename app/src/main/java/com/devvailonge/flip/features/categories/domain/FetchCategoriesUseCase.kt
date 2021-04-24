package com.devvailonge.flip.features.categories.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.categories.data.CategoryDao
import com.devvailonge.flip.features.categories.presentation.CategoryListState

class FetchCategoriesUseCase(
    application: Application,
    private val categoryDao: CategoryDao = AppDataBase.getDataBase(application).categoryDao()
) {

    fun perform(): LiveData<CategoryListState> {
        return liveData {
            try {
                emit(CategoryListState.Loading)
                val source = categoryDao
                    .getAllCategories()
                    .map { list ->
                        if (list.isEmpty()) {
                            CategoryListState.Empty
                        } else {
                            CategoryListState.CategoryList(list)
                        }

                    }

                emitSource(source)
            } catch (exc: Exception) {
                emit(CategoryListState.ErrorMessage(R.string.category_error))
            }
        }

    }

    companion object {
        fun create(): FetchCategoriesUseCase {
            return FetchCategoriesUseCase(FlipApplication.instance)
        }
    }
}