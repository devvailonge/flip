package com.devvailonge.flip.features.categories.list.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentCategoryListBinding
import com.devvailonge.flip.features.categories.create.domain.CreateCategoryUseCase
import com.devvailonge.flip.features.categories.list.presentation.CategoryListEvent
import com.devvailonge.flip.features.categories.list.presentation.CategoryListState
import com.devvailonge.flip.features.categories.list.presentation.CategoryListViewModel
import com.devvailonge.flip.utils.viewBinding


class CategoryListFragment: Fragment(R.layout.fragment_category_list) {

    private val binding by viewBinding(FragmentCategoryListBinding::bind)
    private val adapter by lazy { CategoryListAdapter() }

    private lateinit var viewModel: CategoryListViewModel

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CategoryListViewModel.CategoryListViewModelFactory()
        ).get(CategoryListViewModel::class.java)

    }

    private fun setObserver() {
        viewModel.state.observe(this, {
            updateState(it)
        })
    }

    private fun updateState(state: CategoryListState) {
        Log.d("Roquee", state.toString())
        when (state) {
            is CategoryListState.CategoryList -> {
                binding.vfCategoryList.displayedChild = SUCCESS
                binding.txtCategoryTitle.setText(R.string.app_name)
                adapter.submitList(state.list)
            }
            is CategoryListState.ErrorMessage -> {
                binding.vfCategoryList.displayedChild = EMPTY
                binding.txtCategoryTitle.setText(state.message)
                binding.imgCategoryList.setImageResource(R.drawable.ic_category_empty)
            }
            is CategoryListState.Loading -> {
                binding.txtCategoryTitle.setText(R.string.loading)
                binding.vfCategoryList.displayedChild = LOADING
            }
            CategoryListState.Empty -> {
                binding.vfCategoryList.displayedChild = EMPTY
                binding.txtCategoryTitle.setText(R.string.category_empty_message)
                binding.imgCategoryList.setImageResource(R.drawable.ic_category_empty)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setObserver()
        viewModel.dispatch(CategoryListEvent.Fetch)
        /**
         * Just for test
         */
        CreateCategoryUseCase.populateItems()
            .observe(this, {
                Log.d("Roquee", it.toString())
            })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCategoryList.adapter = adapter
        binding.fabCategoryList.setOnClickListener {
            findNavController().navigate(
                R.id.presentCreateCategory
            )
        }
    }

    companion object{
        private const val LOADING = 0
        private const val EMPTY = 1
        private const val SUCCESS = 2
    }

}