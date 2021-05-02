package com.devvailonge.flip.features.categories.create.presentation

import androidx.lifecycle.*
import com.devvailonge.flip.features.categories.create.domain.CreateCategoryUseCase
import com.devvailonge.flip.features.categories.create.domain.SelectCategoryImageUseCase

/**
 * Bridge btw category creation business logic
 * and UI
 */
class CategoryCreateViewModel(
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val selectCategoryImageUseCase: SelectCategoryImageUseCase
) : ViewModel() {

    private val event = MutableLiveData<CategoryCreateEvent>()

    val state: LiveData<CategoryCreateState> = event.switchMap {
        when (it) {
            is CategoryCreateEvent.Insert -> createCategoryUseCase.perform(
                it.name,
                selectCategoryImageUseCase.categoryImage.value
            )
            is CategoryCreateEvent.SelectCategoryImage -> selectCategoryImageUseCase.perform(it.categoryImage)
        }
    }

    fun dispatch(event: CategoryCreateEvent) {
        this.event.postValue(event)
    }

    class CreateCategoryViewModelFactory constructor(
        private val createCategoryUseCase: CreateCategoryUseCase = CreateCategoryUseCase.create()
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CategoryCreateViewModel::class.java)) {
                CategoryCreateViewModel(
                    this.createCategoryUseCase,
                    SelectCategoryImageUseCase.create()
                ) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}