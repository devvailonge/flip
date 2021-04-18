package com.devvailonge.flip.features.categories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.flip.R
import com.devvailonge.flip.features.categories.data.generateCategoryList

class CategoryListFragment: AppCompatActivity() {

    lateinit var rvCategoryImageList:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_category_image_list)
        rvCategoryImageList = findViewById(R.id.rvCategoryImageList)
        rvCategoryImageList.adapter = CategoryImageListAdapter(generateCategoryList())
    }
}