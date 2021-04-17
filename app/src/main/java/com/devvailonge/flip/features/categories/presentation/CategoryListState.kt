package com.devvailonge.flip.features.categories.presentation

import androidx.annotation.StringRes
import com.devvailonge.flip.features.categories.data.CategoryEntity

sealed class CategoryListState {
    data class CategoryList(val list: List<CategoryEntity>) : CategoryListState()
    data class ErrorMessage(@StringRes val message : Int) : CategoryListState()
    data class Loading(val isLoading : Boolean) : CategoryListState()
    object Empty : CategoryListState()
}