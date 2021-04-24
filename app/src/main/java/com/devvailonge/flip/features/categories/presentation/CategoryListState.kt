package com.devvailonge.flip.features.categories.presentation

import androidx.annotation.StringRes
import com.devvailonge.flip.features.categories.data.CategoryEntity

sealed class CategoryListState {
    data class CategoryList(val list: List<CategoryEntity>) : CategoryListState()
    data class ErrorMessage(@StringRes val message : Int) : CategoryListState()
    object Loading : CategoryListState()
    object Empty : CategoryListState()
}