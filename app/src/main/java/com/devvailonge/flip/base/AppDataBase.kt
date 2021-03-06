package com.devvailonge.flip.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devvailonge.flip.features.categories.data.CategoryDao
import com.devvailonge.flip.features.categories.data.CategoryEntity
import com.devvailonge.flip.features.flashcard.data.FlashCardDao
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity


@Database(entities = [CategoryEntity::class, FlashCardEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun flashcardDao(): FlashCardDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also {
                    instance = it
                }
            }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, AppDataBase::class.java, "category_table")
                .fallbackToDestructiveMigration()
                .build()
    }
}