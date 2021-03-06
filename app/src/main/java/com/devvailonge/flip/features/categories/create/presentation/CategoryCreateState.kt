package com.devvailonge.flip.features.categories.create.presentation

import androidx.annotation.StringRes
import com.devvailonge.flip.features.categories.data.CategoryImage

/**
 * Parent class that contains all UI states
 * for category creation operations
 */
sealed class CategoryCreateState {

    /**
     * Display spinner
     */
    object Loading : CategoryCreateState()

    /**
     * Display string res message
     */
    data class Error(@StringRes val message: Int) : CategoryCreateState()

    data class Success(val categoryId: Long, @StringRes val message: Int) : CategoryCreateState()

    data class CategorySelected(val categoryImage: CategoryImage): CategoryCreateState()
}