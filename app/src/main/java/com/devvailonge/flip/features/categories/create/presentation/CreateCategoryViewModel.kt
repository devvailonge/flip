package com.devvailonge.flip.features.categories.create.presentation

import androidx.lifecycle.*
import com.devvailonge.flip.features.categories.create.domain.CreateCategoryUseCase
import com.devvailonge.flip.features.categories.list.presentation.CategoryListViewModel

/**
 * Bridge btw category creation business logic
 * and UI
 */
class CreateCategoryViewModel(
    private val createCategoryUseCase: CreateCategoryUseCase
) : ViewModel() {

    private val event = MutableLiveData<CreateCategoryEvent>()

    val state: LiveData<CreateCategoryState> = event.switchMap {
        when (it) {
            is CreateCategoryEvent.Insert -> createCategoryUseCase.perform(
                it.name,
                it.categoryImage
            )
        }
    }

    fun dispatch(event: CreateCategoryEvent) {
        this.event.postValue(event)
    }

    class CreateCategoryViewModelFactory constructor(
        private val createCategoryUseCase: CreateCategoryUseCase = CreateCategoryUseCase.create()
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
                CreateCategoryViewModel(
                    this.createCategoryUseCase
                ) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}