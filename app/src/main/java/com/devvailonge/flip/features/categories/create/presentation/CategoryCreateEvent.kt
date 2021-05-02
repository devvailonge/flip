package com.devvailonge.flip.features.categories.create.presentation

import com.devvailonge.flip.features.categories.data.CategoryImage

/**
 * Parent class contains all events
 * to handle category create operations
 */
sealed class CategoryCreateEvent {

    /**
     * Trigger when user click on save button
     * @param name the given category name
     * @param categoryImage the selected category image
     */
    data class Insert(val name: String, val categoryImage: CategoryImage): CategoryCreateEvent()
}