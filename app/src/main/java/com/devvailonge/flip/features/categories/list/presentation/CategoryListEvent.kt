package com.devvailonge.flip.features.categories.list.presentation

sealed class CategoryListEvent {
    object Fetch : CategoryListEvent()
}