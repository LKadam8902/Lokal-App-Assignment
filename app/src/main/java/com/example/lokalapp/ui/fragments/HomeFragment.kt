package com.example.lokalapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokalapp.adapters.JobsAdapter
import com.example.lokalapp.databinding.FragmentHomeBinding
import com.example.lokalapp.db.Job.JobDatabase
import com.example.lokalapp.ui.activities.ActivityJob
import com.example.lokalapp.viewmodel.JobsViewModel
import com.example.lokalapp.viewmodel.JobsViewModelFactory


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var JobAdapter: JobsAdapter
     private lateinit var jobsViewModel: JobsViewModel

    companion object {
        const val JOB_ID = "com.example.lokalapp.fragments.jobId"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val jobDao = JobDatabase.getInstance(requireContext()).jobDao()

        // Create the ViewModel with the custom factory
        val factory = JobsViewModelFactory(jobDao)
        jobsViewModel = ViewModelProvider(this, factory)[JobsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        observe()

        onJobClick()
    }

    private fun onJobClick() {
        JobAdapter
    }

    private fun observe() {
        jobsViewModel.observeAllJobs().observeForever { jobs ->
            if (jobs != null) {
                Log.d("observe application", "Application received")
                JobAdapter.submitList(jobs)
            } else {
                Log.d("observe application", "No application received")
            }
            Log.d("observe application", "Application is observed")
        }
    }

    private fun prepareRecyclerView() {
        jobsViewModel.getAllJobs()
        JobAdapter = JobsAdapter { job ->
            val intent = Intent(activity, ActivityJob::class.java)
            intent.putExtra(JOB_ID,job.id)
            startActivity(intent)
            Log.d("Job Clicked", "Job Title: ${job.title}, Company: ${job.primary_details?.Place}")
        }

        binding.rvJobview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = JobAdapter
        }
    }

}