package com.example.lokalapp.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lokalapp.databinding.ActivityJobBinding
import com.example.lokalapp.db.Job.BookMark
import com.example.lokalapp.db.Job.BookMarkDatabase
import com.example.lokalapp.db.Job.Job
import com.example.lokalapp.db.Job.JobDatabase
import com.example.lokalapp.ui.fragments.HomeFragment
import com.example.lokalapp.viewmodel.BookMarkViewModel
import com.example.lokalapp.viewmodel.BookMarkViewModelFactory
import com.example.lokalapp.viewmodel.JobsViewModel
import com.example.lokalapp.viewmodel.JobsViewModelFactory

class ActivityJob : AppCompatActivity() {

    private lateinit var binding: ActivityJobBinding
    private lateinit var jobsViewModel: JobsViewModel
    private lateinit var bookMarkViewModel: BookMarkViewModel
    private lateinit var progressbar:ProgressBar
    private var jobId: Int = 0

    private var ID: Int = 0
    private var TITLE: String? = null
    private var SALARY: String? = null
    private var LOCATION: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressbar=binding.progressBar
        val jobDao = JobDatabase.getInstance(this).jobDao()

        // Create the ViewModel with the custom factory
        val factory = JobsViewModelFactory(jobDao)
        jobsViewModel = ViewModelProvider(this, factory)[JobsViewModel::class.java]

        val bkDao = BookMarkDatabase.getInstance(this).bookMarkDao()

        // Create the ViewModel with the custom factory
        val bkfactory = BookMarkViewModelFactory(bkDao)
        bookMarkViewModel = ViewModelProvider(this, bkfactory)[BookMarkViewModel::class.java]

        getJobInformationFromIntent()
        jobsViewModel.getJobById(jobId)
        observeJobDetails()

        binding.btnBookmark.setOnClickListener {
            bookMarkViewModel.addBookmark(BookMark(ID, TITLE, SALARY, LOCATION))
        }


    }

    private fun observeJobDetails() {
        jobsViewModel.observeJob().observeForever { job ->
            job?.let {
                progressbar.visibility=View.GONE
                setInformationInViews(it)
            }
        }
    }

    private fun setInformationInViews(job: Job) {
        binding.apply {
            ID = job.id
            TITLE = job.title
            title.text = job.title
            SALARY = job.Salary
            salary.text = job.Salary
            LOCATION = job.Location
            location.text = job.Location

            // set other views similarly
        }
    }


    private fun getJobInformationFromIntent() {
        val intent = intent
        jobId = intent.getIntExtra(HomeFragment.JOB_ID, -1)
    }


}