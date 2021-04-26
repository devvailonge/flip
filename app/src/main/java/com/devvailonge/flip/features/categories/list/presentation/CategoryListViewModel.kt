package com.devvailonge.flip.features.categories.list.presentation

import androidx.lifecycle.*
import com.devvailonge.flip.features.categories.list.domain.FetchCategoriesUseCase

class CategoryListViewModel(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : ViewModel() {

    private val event = MutableLiveData<CategoryListEvent>()

    val state: LiveData<CategoryListState> = event.switchMap {
        when (it) {
            CategoryListEvent.Fetch -> fetchCategoriesUseCase.perform()
        }
    }

    fun dispatch(event: CategoryListEvent) {
        this.event.postValue(event)
    }

    class CategoryListViewModelFactory constructor(
        private val fetchCategoriesUseCase: FetchCategoriesUseCase = FetchCategoriesUseCase.create()
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
                CategoryListViewModel(
                    this.fetchCategoriesUseCase
                ) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}