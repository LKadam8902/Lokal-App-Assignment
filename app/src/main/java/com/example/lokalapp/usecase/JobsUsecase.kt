package com.example.lokalapp.usecase

import JobRepository
import androidx.lifecycle.MutableLiveData
import com.example.lokalapp.db.Job.Job
import com.example.lokalapp.pojo.Result

class JobsUsecase(private val repository: JobRepository) {

    var jobs = MutableLiveData<List<Result>>()
    var job = MutableLiveData<Job>()

    fun getAllJobs(): MutableLiveData<List<Result>> {
        repository.getAllJobs().observeForever {
            jobs.postValue(it)
        }
        return jobs
    }

    fun getJobById(id: Int): MutableLiveData<Job> {
        repository.getJobById(id).observeForever {
            job.postValue(it)
        }
        return job
    }
}