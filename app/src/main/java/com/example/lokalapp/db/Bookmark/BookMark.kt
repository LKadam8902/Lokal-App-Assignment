package com.example.lokalapp.db.Job

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BookMark_Table")
data class BookMark(
    @PrimaryKey
    val id: Int,
    var title:String?=null,
    var Salary:String?=null,
    var Location:String?=null
)
