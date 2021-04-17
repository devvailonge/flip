package com.devvailonge.flip.features.categories.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.devvailonge.flip.R

enum class CategoryImage(
    id: Int,
    @DrawableRes image: Int,
    @ColorRes bg: Int
) {

    LANGUAGE(
        1,
        R.drawable.ic_category_empty,
        R.color.black
    ),

    DEFAULT(
        1,
        R.drawable.ic_onboarding_one,
        R.color.purple_200
    )

}
