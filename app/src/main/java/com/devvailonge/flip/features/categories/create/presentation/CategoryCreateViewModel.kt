package com.devvailonge.flip.features.categories.create.presentation

import androidx.lifecycle.*
import com.devvailonge.flip.features.categories.create.domain.CreateCategoryUseCase
import com.devvailonge.flip.features.categories.list.presentation.CategoryListViewModel

/**
 * Bridge btw category creation business logic
 * and UI
 */
class CategoryCreateViewModel(
    private val createCategoryUseCase: CreateCategoryUseCase
) : ViewModel() {

    private val event = MutableLiveData<CategoryCreateEvent>()

    val state: LiveData<CategoryCreateState> = event.switchMap {
        when (it) {
            is CategoryCreateEvent.Insert -> createCategoryUseCase.perform(
                it.name,
                it.categoryImage
            )
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
            return if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
                CategoryCreateViewModel(
                    this.createCategoryUseCase
                ) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}