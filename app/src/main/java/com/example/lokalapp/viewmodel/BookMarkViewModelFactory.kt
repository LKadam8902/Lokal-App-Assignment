package com.example.lokalapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lokalapp.db.Job.BookMark
import com.example.lokalapp.db.Job.BookMarkDao
import com.example.lokalapp.db.Job.JobDao

class BookMarkViewModelFactory(private val bookDao: BookMarkDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookMarkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookMarkViewModel(bookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}