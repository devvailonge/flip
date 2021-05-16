package com.devvailonge.flip.features.categories.create.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.categories.create.presentation.CategoryCreateState
import com.devvailonge.flip.features.categories.data.CategoryDao
import com.devvailonge.flip.features.categories.data.CategoryEntity
import com.devvailonge.flip.features.categories.data.CategoryImage

class CreateCategoryUseCase(
    application: Application,
    private val categoryDao: CategoryDao = AppDataBase.getDataBase(application).categoryDao()
) {

    fun perform(name: String, categoryImage: CategoryImage?): LiveData<CategoryCreateState> {
        return liveData {
            emit(CategoryCreateState.Loading)
            try {
                name
                    .trim()
                    .takeIf { it.isNotEmpty() }
                    ?.let {
                        categoryImage?.let {
                            val result = categoryDao
                                .insert(CategoryEntity(name = name, categoryImage = it.name))
                            emit(CategoryCreateState.Success(result, R.string.create_category_success))
                        } ?: run {
                            emit(CategoryCreateState.Error(R.string.missing_category_image))
                        }
                    } ?: run{
                    emit(CategoryCreateState.Error(R.string.missing_category_name))
                }

            } catch (exception: Exception) {
                emit(CategoryCreateState.Error(R.string.create_category_failed))
            }
        }
    }


    companion object {

        fun create(): CreateCategoryUseCase {
            return CreateCategoryUseCase(FlipApplication.instance)
        }

    }
}