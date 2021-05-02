package com.devvailonge.flip.features.categories.create.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentCategoryCreateBinding
import com.devvailonge.flip.features.categories.create.presentation.CategoryCreateEvent
import com.devvailonge.flip.features.categories.create.presentation.CategoryCreateState
import com.devvailonge.flip.features.categories.create.presentation.CategoryCreateViewModel
import com.devvailonge.flip.features.categories.data.CategoryImage
import com.devvailonge.flip.features.categories.images.CategoryImageListFragment
import com.devvailonge.flip.utils.viewBinding

class CategoryCreateFragment : Fragment(R.layout.fragment_category_create) {

    private val binding by viewBinding(FragmentCategoryCreateBinding::bind)

    private lateinit var viewModel: CategoryCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CategoryCreateViewModel.CreateCategoryViewModelFactory()
        ).get(CategoryCreateViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        setObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ctnSelectImageCategoryCreate.setOnClickListener {
            findNavController().navigate(
                R.id.presentCategoryImageList
            )
        }

        binding.btnSaveCategory.setOnClickListener {
            viewModel.dispatch(CategoryCreateEvent.Insert(
                name = binding.edtCategoryNameCreate.text.toString()
            ))
        }

        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>(CategoryImageListFragment.KEY_SELECTED_IMAGE_NAME)
            ?.observe(viewLifecycleOwner) { data ->
                viewModel.dispatch(CategoryCreateEvent.SelectCategoryImage(CategoryImage.valueOf(data)))
            }
    }

    private fun setObserver() {
        viewModel.state.observe(this, {
            updateState(it)
        })
    }

    private fun updateState(state: CategoryCreateState) {
        when(state){
            is CategoryCreateState.CategorySelected -> {
                binding.ctnSelectImageCategoryCreate.setBackgroundResource(state.categoryImage.bg)
                binding.imgCreateCategory.setImageResource(state.categoryImage.image)
            }
            is CategoryCreateState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            CategoryCreateState.Loading -> {

            }
            is CategoryCreateState.Success -> {
                findNavController().navigateUp()
            }
        }
    }

}