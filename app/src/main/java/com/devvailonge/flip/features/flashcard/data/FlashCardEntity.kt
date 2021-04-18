package com.devvailonge.flip.features.flashcard.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.devvailonge.flip.features.categories.data.CategoryEntity

@Entity(tableName = "flashcard_table", foreignKeys = [ForeignKey(entity = CategoryEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("categoryId"),
    onDelete = ForeignKey.CASCADE)]
)
data class FlashCardEntity (
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val frontText: String,
        val backText: String,
        val categoryId: Long
)