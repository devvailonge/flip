package com.devvailonge.flip.features.flashcard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FlashCardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFlashCard(flashCard: FlashCardEntity)

    @Query("SELECT * FROM flashcard_table ORDER BY id ASC ")
    fun getAllFlashCards(): LiveData<List<FlashCardEntity>>


    @Query("SELECT * FROM flashcard_table  WHERE categoryId = :categoryId ORDER BY id ASC ")
    fun getFlashcardByCategory(categoryId: Long): LiveData<List<FlashCardEntity>>

    @Update
    fun updateFlashCard(flashCard: FlashCardEntity)

    @Delete
    fun deleteFlashCard(flashCard: FlashCardEntity)

    /*@Query("DELETE FROM flashcard_table")
    fun deleteAllFlashCards()*/

}