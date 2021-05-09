package com.devvailonge.flip.features.flashcard.list.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentFlashcardListBinding
import com.devvailonge.flip.utils.viewBinding

class FlashCardListFragment : Fragment(R.layout.fragment_flashcard_list) {

    private val binding by viewBinding(FragmentFlashcardListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabFlashcardList.setOnClickListener {
            findNavController().navigate(
                R.id.presentFlashCardCreate
            )
        }
    }

    companion object {
        const val EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID"
    }
}