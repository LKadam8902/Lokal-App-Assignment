package com.example.lokalapp.viewmodel

import JobRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lokalapp.db.Job.Job
import com.example.lokalapp.db.Job.JobDao
import com.example.lokalapp.pojo.Result
import com.example.lokalapp.usecase.JobsUsecase

class JobsViewModel(
    private  val dao: JobDao
) : ViewModel() {

    private var repository: JobRepository = JobRepository(dao)
    private var JobsUseCase: JobsUsecase =
        JobsUsecase(repository)
    var jobsLiveData = MutableLiveData<List<Result>?>()
    var jobLiveData = MutableLiveData<Job>()
    fun getAllJobs() {
        JobsUseCase.getAllJobs().observeForever {
            jobsLiveData.postValue(it)
            Log.d("job vm", "reached vm")
        }
    }

    fun observeAllJobs() = jobsLiveData

    fun getJobById(jobId: Int) {
        JobsUseCase.getJobById(jobId).observeForever {
            jobLiveData.postValue(it)
        }
    }

    fun observeJob(): MutableLiveData<Job> {
        return jobLiveData
    }

}