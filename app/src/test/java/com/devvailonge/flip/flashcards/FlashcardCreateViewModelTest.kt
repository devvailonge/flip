package com.devvailonge.flip.flashcards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.devvailonge.flip.features.flashcard.create.domain.InsertFlashCardUseCase
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateEvent
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateState
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateViewModel
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
class FlashcardCreateViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val insertFlashCardUseCase: InsertFlashCardUseCase = mock()
    private val testee = FlashCardCreateViewModel(insertFlashCardUseCase)

    @Test
    fun `test create use case`() {
        //Given
        val frontText = "Front Text"
        val backText = "Back Text"
        val categoryId: Long = 2
        val state = FlashCardCreateState.Loading
        whenever(insertFlashCardUseCase.perform(frontText, backText, categoryId)).thenReturn(MutableLiveData(state))

        //When
        testee.dispatch(FlashCardCreateEvent.Insert(frontText, backText , categoryId))
        val result = testee.state.waitForValue()

        //Then
        assertEquals(state, result)
        verify(insertFlashCardUseCase).perform(frontText, backText, categoryId)

    }
}