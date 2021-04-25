package com.devvailonge.flip.features.categories.create.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devvailonge.flip.FlipApplication
import com.devvailonge.flip.R
import com.devvailonge.flip.base.AppDataBase
import com.devvailonge.flip.features.categories.create.presentation.CreateCategoryState
import com.devvailonge.flip.features.categories.data.CategoryDao
import com.devvailonge.flip.features.categories.data.CategoryEntity
import com.devvailonge.flip.features.categories.data.CategoryImage

class CreateCategoryUseCase(
    application: Application,
    private val categoryDao: CategoryDao = AppDataBase.getDataBase(application).categoryDao()
) {

    fun perform(name: String, categoryImage: CategoryImage): LiveData<CreateCategoryState> {
        return liveData {
            emit(CreateCategoryState.Loading)
            try {
                val result = categoryDao
                    .insert(CategoryEntity(name = name, categoryImage = categoryImage.name))
                emit(CreateCategoryState.Success(result, R.string.create_category_success))
            } catch (exception: Exception) {
                emit(CreateCategoryState.Error(R.string.create_category_failed))
            }
        }
    }

    fun perform(entity: CategoryEntity): LiveData<Long> {
        return liveData {
            emit(
                categoryDao
                    .insert(entity)
            )
        }
    }

    companion object {

        fun create(): CreateCategoryUseCase {
            return CreateCategoryUseCase(FlipApplication.instance)
        }

        /**
         * Just for testing
         */
        fun populateItems(): LiveData<Long> {
            return liveData {
                emitSource(
                    create().perform(
                        CategoryEntity(
                            id = 1,
                            name = "Alemao A1",
                            categoryImage = CategoryImage.LANGUAGE.name
                        )
                    )
                )
                emitSource(
                    create().perform(
                        CategoryEntity(
                            id = 2,
                            name = "Capitais",
                            categoryImage = CategoryImage.GEO.name
                        )
                    )
                )
                emitSource(
                    create().perform(
                        CategoryEntity(
                            id = 3,
                            name = "Historia",
                            categoryImage = CategoryImage.HISTORY.name
                        )
                    )
                )
                emitSource(
                    create().perform(
                        CategoryEntity(
                            id = 4,
                            name = "Matematica",
                            categoryImage = CategoryImage.MATH.name
                        )
                    )
                )
                emitSource(
                    create().perform(
                        CategoryEntity(
                            id = 5,
                            name = "Default",
                            categoryImage = CategoryImage.DEFAULT.name
                        )
                    )
                )
            }

        }
    }
}