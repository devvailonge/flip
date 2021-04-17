package com.devvailonge.flip.features.categories.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val imageId: Int,
    val bgColor: Int
)