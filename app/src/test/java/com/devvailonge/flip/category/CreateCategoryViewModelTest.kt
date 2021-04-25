package com.devvailonge.flip.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.devvailonge.flip.features.categories.create.domain.CreateCategoryUseCase
import com.devvailonge.flip.features.categories.create.presentation.CreateCategoryEvent
import com.devvailonge.flip.features.categories.create.presentation.CreateCategoryState
import com.devvailonge.flip.features.categories.create.presentation.CreateCategoryViewModel
import com.devvailonge.flip.features.categories.data.CategoryImage
import com.devvailonge.flip.waitForValue
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CreateCategoryViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val createUseCase: CreateCategoryUseCase = mock()
    private val testee = CreateCategoryViewModel(createUseCase)

    @Test
    fun `test create use case`() {
        //Given
        val name = "Category Name"
        val categoryImage = CategoryImage.HISTORY
        val state = CreateCategoryState.Loading
        whenever(createUseCase.perform(name, categoryImage)).thenReturn(MutableLiveData(state))

        //When
        testee.dispatch(CreateCategoryEvent.Insert(name, categoryImage))
        val result = testee.state.waitForValue()

        //Then
        assertEquals(state, result)
        verify(createUseCase).perform(name, categoryImage)

    }
}