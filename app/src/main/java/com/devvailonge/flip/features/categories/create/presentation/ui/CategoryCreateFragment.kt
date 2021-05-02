package com.devvailonge.flip.features.categories.create.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentCategoryCreateBinding
import com.devvailonge.flip.features.categories.create.presentation.CategoryCreateViewModel
import com.devvailonge.flip.utils.viewBinding

class CategoryCreateFragment: Fragment(R.layout.fragment_category_create) {

    private val binding by viewBinding(FragmentCategoryCreateBinding::bind)

    private lateinit var viewModel: CategoryCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CategoryCreateViewModel.CreateCategoryViewModelFactory()
        ).get(CategoryCreateViewModel::class.java)

    }


}