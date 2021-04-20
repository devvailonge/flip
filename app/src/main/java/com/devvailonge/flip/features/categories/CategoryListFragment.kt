package com.devvailonge.flip.features.categories

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.FragmentCategoryListBinding
import com.devvailonge.flip.utils.viewBinding


class CategoryListFragment: Fragment(R.layout.fragment_category_list) {

    private val binding by viewBinding(FragmentCategoryListBinding::bind)

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
       findNavController().navigate(
         R.id.presentCountryDetail,
         bundleOf(CountryDetailFragment.COUNTRY_NAME_EXTRA to countryDto.name)
     )
      */
    }

}