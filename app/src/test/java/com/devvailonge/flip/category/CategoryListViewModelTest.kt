package com.devvailonge.flip.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.devvailonge.flip.features.categories.list.domain.FetchCategoriesUseCase
import com.devvailonge.flip.features.categories.list.presentation.CategoryListEvent
import com.devvailonge.flip.features.categories.list.presentation.CategoryListState
import com.devvailonge.flip.features.categories.list.presentation.CategoryListViewModel
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
class CategoryListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val fetchUseCase: FetchCategoriesUseCase = mock()
    private val testee = CategoryListViewModel(fetchUseCase)

    @Test
    fun `test fetch use case`() {
        //Given
        val state = CategoryListState.Empty
        whenever(fetchUseCase.perform()).thenReturn(MutableLiveData(state))

        //When
        testee.dispatch(CategoryListEvent.Fetch)
        val result = testee.state.waitForValue()

        //Then
        assertEquals(state, result)
        verify(fetchUseCase).perform()

    }
}