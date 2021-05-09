package com.devvailonge.flip.features.flashcard.create.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentFlashcardCreateBinding
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateState
import com.devvailonge.flip.features.flashcard.create.presentation.FlashCardCreateViewModel
import com.devvailonge.flip.utils.viewBinding

class FlashCardCreateFragment: Fragment(R.layout.fragment_flashcard_create) {

    private val binding by viewBinding(FragmentFlashcardCreateBinding::bind)
    private lateinit var viewModel: FlashCardCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            FlashCardCreateViewModel.FlashCardCreateViewModelFactory()
        ).get(FlashCardCreateViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        setObserver()
    }

    private fun setObserver() {
        viewModel.state.observe(this, {
            updateState(it)
        })
    }

    private fun updateState(state: FlashCardCreateState) {
        print(state)
    }
}