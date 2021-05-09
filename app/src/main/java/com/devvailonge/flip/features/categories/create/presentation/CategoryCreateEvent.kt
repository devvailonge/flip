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
     */
    data class Insert(val name: String): CategoryCreateEvent()


    data class SelectCategoryImage(val categoryImage: CategoryImage): CategoryCreateEvent()
}