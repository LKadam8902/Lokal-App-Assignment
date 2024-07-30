package com.example.lokalapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lokalapp.db.Job.JobDao

class JobsViewModelFactory(private val jobDao: JobDao) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobsViewModel(jobDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}