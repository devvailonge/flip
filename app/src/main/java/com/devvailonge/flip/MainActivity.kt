package com.devvailonge.flip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.devvailonge.flip.databinding.ActivityMainBinding
import com.devvailonge.flip.features.categories.presentation.CategoryListEvent
import com.devvailonge.flip.features.categories.presentation.CategoryListState
import com.devvailonge.flip.features.categories.presentation.CategoryListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryListViewModel


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            CategoryListViewModel.CategoryListViewModelFactory()
        ).get(CategoryListViewModel::class.java)

        setObserver()

        viewModel.dispatch(CategoryListEvent.Fetch)
    }

    private fun setObserver() {
        viewModel.state.observe(this, {
            updateState(it)
        })
    }

    private fun updateState(state: CategoryListState) {
        when (state) {
            is CategoryListState.CategoryList -> {
                state.list
            }
            is CategoryListState.ErrorMessage -> {
                state.message
            }
            is CategoryListState.Loading -> {
               binding.loading.isVisible = state.isLoading
            }
            CategoryListState.Empty -> {
                binding.imgTest.setImageResource(R.drawable.ic_category_empty)
            }
        }
    }

}



