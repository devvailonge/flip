package com.devvailonge.flip.features.flashcard.list.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentFlashcardListBinding
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListEvent
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListState
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListViewModel
import com.devvailonge.flip.utils.viewBinding

class FlashCardListFragment : Fragment(R.layout.fragment_flashcard_list) {

    private val binding by viewBinding(FragmentFlashcardListBinding::bind)
    private lateinit var viewModel: FlashCardListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = arguments?.getLong(FlashCardListFragment.EXTRA_CATEGORY_ID)
            ?: throw IllegalArgumentException("Missing category id")

        binding.fabFlashcardList.setOnClickListener {
            findNavController().navigate(
                R.id.presentFlashCardCreate,
                bundleOf(EXTRA_CATEGORY_ID to categoryId)

            )
        }

        viewModel.dispatch(FlashCardListEvent.Fetch(categoryId))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            FlashCardListViewModel.FlashCardListViewModelFactory()
        ).get(FlashCardListViewModel::class.java)
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

    private fun updateState(state: FlashCardListState) {
        print(state)
    }

    companion object {
        const val EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID"
    }
}