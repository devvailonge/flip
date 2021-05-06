package com.devvailonge.flip.features.categories.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.flip.R
import com.devvailonge.flip.features.categories.data.CategoryImage

class CategoryImageListAdapter(
    private val categoryImages: List<CategoryImage>,
    private val callback: (String) -> Unit
) : RecyclerView.Adapter<CategoryImageListAdapter.CategoryImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_category_image, parent, false)
        return CategoryImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryImageViewHolder, position: Int) {
        val item = categoryImages[position]
        holder.bind(item, callback)
    }

    override fun getItemCount(): Int {
        return categoryImages.size
    }

    class CategoryImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var ctnImage: LinearLayout
        lateinit var imageCategory: ImageView

        fun bind(data: CategoryImage, callback: (String) -> Unit) {
            ctnImage = view.findViewById(R.id.ctnCategoryImageItem)
            imageCategory = view.findViewById(R.id.imgCategoryImageItem)

            ctnImage.setBackgroundColor(ctnImage.context.getColor(data.bg))
            imageCategory.setImageDrawable(imageCategory.context.getDrawable(data.image))

            ctnImage.setOnClickListener {
                callback.invoke(data.name)
            }
        }
    }
}


