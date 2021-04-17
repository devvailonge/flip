package com.devvailonge.flip.features.categories.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table ORDER BY name ASC")
    fun  getAllCategories(): LiveData<List<CategoryEntity>>

}