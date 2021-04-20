package com.devvailonge.flip.features.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.flip.R
import com.devvailonge.flip.features.categories.data.CategoryImage

class CategoryImageListAdapter(private val categoryImages: List<CategoryImage>)
    : RecyclerView.Adapter<CategoryImageListAdapter.CategoryImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryImageListAdapter.CategoryImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_category_image,parent,false)
        return CategoryImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder:CategoryImageListAdapter.CategoryImageViewHolder, position: Int) {
        val item = categoryImages[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return categoryImages.size
    }

    class CategoryImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var ctnImage: LinearLayout
        lateinit var imageCategory: ImageView

        fun bind(data: CategoryImage) {
            ctnImage = view.findViewById(R.id.ctnCategoryItem)
            imageCategory = view.findViewById(R.id.imgCategoryItem)

            ctnImage.setBackgroundColor(ctnImage.context.getColor(data.bg))
            imageCategory.setImageDrawable(imageCategory.context.getDrawable(data.image))
        }
    }
}


