package com.devvailonge.flip.features.categories.images

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentCategoryImageListBinding
import com.devvailonge.flip.features.categories.data.CategoryImage
import com.devvailonge.flip.utils.viewBinding

class CategoryImageListFragment: Fragment(R.layout.fragment_category_image_list) {

    private val binding by viewBinding(FragmentCategoryImageListBinding::bind)
    private lateinit var adapter: CategoryImageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoryImageListAdapter(CategoryImage.values().toList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCategoryImageList.adapter = adapter
    }

}