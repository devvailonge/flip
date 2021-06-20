package com.devvailonge.flip.features.flashcard.list.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentFlashcardListBinding
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity
import com.devvailonge.flip.features.flashcard.delete.presentation.ui.FlashCardDeleteDialog
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListEvent
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListState
import com.devvailonge.flip.features.flashcard.list.presentation.FlashCardListViewModel
import com.devvailonge.flip.utils.viewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private const val DELETE_ARTICLE_DIALOG_TAG = "CancelDialog"

class FlashCardListFragment : Fragment(R.layout.fragment_flashcard_list) {

    private val binding by viewBinding(FragmentFlashcardListBinding::bind)
    private var categoryId: Long = 0
    private lateinit var viewModel: FlashCardListViewModel
    private val adapter: FlashCardListAdapter
            by lazy { FlashCardListAdapter(::deleteClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryId = arguments?.getLong(EXTRA_CATEGORY_ID)
            ?: throw IllegalArgumentException("Missing category id")

        val categoryName = arguments?.getString(EXTRA_CATEGORY_NAME)
            ?: throw IllegalArgumentException("Missing category name")

        //Update toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.title = categoryName

        binding.fabFlashcardList.setOnClickListener {
            findNavController().navigate(
                R.id.presentFlashCardCreate,
                bundleOf(EXTRA_CATEGORY_ID to categoryId)
            )
        }

        binding.rvFlashcardList.adapter = adapter

        viewModel.dispatch(FlashCardListEvent.Fetch(categoryId))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_category, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(
            this,
            FlashCardListViewModel.FlashCardListViewModelFactory()
        ).get(FlashCardListViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete_category) {
            viewModel.dispatch(FlashCardListEvent.DeleteCategory(categoryId))
        }
        return true
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

    private fun deleteClicked(flashCardEntity: FlashCardEntity) {
        FlashCardDeleteDialog().apply {
            setListener {
                viewModel.dispatch(FlashCardListEvent.Delete(flashCardEntity))
            }
        }.show(parentFragmentManager, DELETE_ARTICLE_DIALOG_TAG)
    }

    private fun updateState(state: FlashCardListState) {
        when (state) {
            FlashCardListState.Empty -> {
                binding.vpFlashcardList.displayedChild = EMPTY
                adapter.submitList(emptyList())
            }
            is FlashCardListState.Message -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
            is FlashCardListState.FlashCardList -> {
                binding.vpFlashcardList.displayedChild = CONTENT
                adapter.submitList(state.list)
            }
            is FlashCardListState.Loading -> {
            }
            is FlashCardListState.DeleteSuccess -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
                viewModel.dispatch(FlashCardListEvent.Fetch(state.categoryId))
            }
            is FlashCardListState.DeleteCategorySuccess -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }
    }

    companion object {
        const val EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID"
        const val EXTRA_CATEGORY_NAME = "EXTRA_CATEGORY_NAME"
        const val EMPTY = 1
        const val CONTENT = 0
    }
}