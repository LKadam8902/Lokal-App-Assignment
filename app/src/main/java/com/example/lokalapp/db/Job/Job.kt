package com.example.lokalapp.db.Job

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Job_Table")
data class Job(
    @PrimaryKey
    val id: Int,
    var title:String?=null,
    var Salary:String?=null,
    var Location:String?=null
)
