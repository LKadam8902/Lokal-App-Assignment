package com.example.lokalapp.db.Job

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookMarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookMark(jobs: BookMark)

    @Query("SELECT * FROM BookMark_Table")
    suspend fun getAllBookMark(): List<BookMark>

    @Query("SELECT * FROM BookMark_Table WHERE id = :jobId")
    suspend fun getBookMarkById(jobId: Int): BookMark?

}