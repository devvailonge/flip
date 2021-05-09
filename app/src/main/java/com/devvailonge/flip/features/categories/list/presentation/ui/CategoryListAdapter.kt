package com.devvailonge.flip.features.categories.list.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.flip.R
import com.devvailonge.flip.features.categories.data.CategoryEntity
import com.devvailonge.flip.features.categories.data.CategoryImage

class CategoryListAdapter(private val clickListener: (CategoryEntity) -> Unit) :
    ListAdapter<CategoryEntity, CategoryListAdapter.CategoryViewHolder>(
        CategoryListAdapter
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    class CategoryViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        lateinit var imageCategory: ImageView
        lateinit var txtCategoryTitle: TextView

        fun bind(data: CategoryEntity, clickListener: (CategoryEntity) -> Unit) {

            val categoryImage = CategoryImage.valueOf(data.categoryImage)
            imageCategory = view.findViewById(R.id.imgCategoryItem)
            txtCategoryTitle = view.findViewById(R.id.txtCategoryTitle)

            txtCategoryTitle.text = data.name
            imageCategory.setBackgroundColor(imageCategory.context.getColor(categoryImage.bg))
            imageCategory.setImageDrawable(imageCategory.context.getDrawable(categoryImage.image))

            view.setOnClickListener {
                clickListener.invoke(data)
            }
        }
    }


    private companion object : DiffUtil.ItemCallback<CategoryEntity>() {

        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem == newItem
        }
    }
}


