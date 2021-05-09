package com.devvailonge.flip.features.categories.create.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.devvailonge.flip.features.categories.create.presentation.CategoryCreateState
import com.devvailonge.flip.features.categories.data.CategoryImage

class SelectCategoryImageUseCase {

    private val selectCategoryImage = MutableLiveData<CategoryImage>()
    val categoryImage: LiveData<CategoryImage> = selectCategoryImage

    fun perform(categoryImage: CategoryImage): LiveData<CategoryCreateState> {
        selectCategoryImage.postValue(categoryImage)
        return selectCategoryImage.map {
            CategoryCreateState.CategorySelected(it)
        }
    }

    companion object {

        fun create(): SelectCategoryImageUseCase {
            return SelectCategoryImageUseCase()
        }
    }
}