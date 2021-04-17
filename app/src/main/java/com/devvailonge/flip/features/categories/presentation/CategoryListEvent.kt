package com.devvailonge.flip.features.categories.presentation

sealed class CategoryListEvent {
    object Fetch : CategoryListEvent()
}