package com.devvailonge.flip.features.categories.create.presentation

import androidx.annotation.StringRes

/**
 * Parent class that contains all UI states
 * for category creation operations
 */
sealed class CreateCategoryState {

    /**
     * Display spinner
     */
    object Loading : CreateCategoryState()

    /**
     * Display string res message
     */
    data class Error(@StringRes val message: Int) : CreateCategoryState()

    data class Success(val categoryId: Long, @StringRes val message: Int) : CreateCategoryState()
}