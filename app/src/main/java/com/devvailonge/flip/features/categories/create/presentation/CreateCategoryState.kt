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
    object Loading: CreateCategoryState()

    /**
     * Display string res message
     * Can be success/failure
     */
    data class Message(@StringRes val message: Int): CreateCategoryState()
}