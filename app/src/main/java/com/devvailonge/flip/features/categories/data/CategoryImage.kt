package com.devvailonge.flip.features.categories.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.devvailonge.flip.R

enum class CategoryImage(
    val id: Int,
    @DrawableRes val image: Int,
    @ColorRes val bg: Int
) {

    LANGUAGE(
        1,
        R.drawable.ic_category_1,
        R.color.category_1
    ),

    GEO(
        2,
        R.drawable.ic_category_2,
        R.color.category_2
    ),

    MATH(
        3,
        R.drawable.ic_category_3,
        R.color.category_3
    ),

    HISTORY(
        4,
        R.drawable.ic_category_4,
        R.color.category_4
    ),

    DEFAULT(
        5,
        R.drawable.ic_category_5,
        R.color.category_5
    )

}
fun generateCategoryList (): List<CategoryImage> {
    return CategoryImage.values().asList()
}