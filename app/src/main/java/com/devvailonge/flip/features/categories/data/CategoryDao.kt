package com.devvailonge.flip.features.categories.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table ORDER BY name ASC")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: CategoryEntity): Long

    @Query("DELETE FROM category_table WHERE id = :categoryId")
    suspend fun deleteCategoryById(categoryId: Long)

    @Delete
    suspend fun delete(category: CategoryEntity)

}