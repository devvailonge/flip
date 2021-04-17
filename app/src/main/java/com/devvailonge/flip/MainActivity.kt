package com.devvailonge.flip

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devvailonge.flip.features.categories.presentation.CategoryListEvent
import com.devvailonge.flip.features.categories.presentation.CategoryListState
import com.devvailonge.flip.features.categories.presentation.CategoryListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryListViewModel
    private lateinit var imageTest: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageTest = findViewById(R.id.imgTest)
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
        Log.d("Roqueeee", state.toString())
        when (state) {
            is CategoryListState.CategoryList -> {
                state.list
            }
            is CategoryListState.ErrorMessage -> {
                state.message
            }
            is CategoryListState.Loading -> {
                state.isLoading
            }
            CategoryListState.Empty -> {
                imageTest.setImageResource(R.drawable.ic_category_empty)
            }
        }
    }

}



