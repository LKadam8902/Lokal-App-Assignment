package com.example.lokalapp.db.Job

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobs(jobs: List<Job>)

    @Query("SELECT * FROM job_table")
    suspend fun getAllJobs(): List<Job>

    @Query("SELECT * FROM job_table WHERE id = :jobId")
    suspend fun getJobById(jobId: Int): Job?

}