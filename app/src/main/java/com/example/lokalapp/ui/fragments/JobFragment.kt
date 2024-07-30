package com.example.lokalapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokalapp.R
import com.example.lokalapp.adapters.BookmarkAdapter
import com.example.lokalapp.adapters.JobsAdapter
import com.example.lokalapp.databinding.FragmentHomeBinding
import com.example.lokalapp.databinding.FragmentJobBinding
import com.example.lokalapp.db.Job.BookMark
import com.example.lokalapp.db.Job.BookMarkDatabase
import com.example.lokalapp.db.Job.JobDatabase
import com.example.lokalapp.ui.activities.ActivityJob
import com.example.lokalapp.viewmodel.BookMarkViewModel
import com.example.lokalapp.viewmodel.BookMarkViewModelFactory
import com.example.lokalapp.viewmodel.JobsViewModel
import com.example.lokalapp.viewmodel.JobsViewModelFactory


class JobFragment : Fragment() {

    private lateinit var binding: FragmentJobBinding
    private lateinit var bkAdapter: BookmarkAdapter
    private lateinit var bookViewModel: BookMarkViewModel

    companion object {
        const val JOB_ID = "com.example.lokalapp.fragments.jobId"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout fo this fragment
        binding = FragmentJobBinding.inflate(layoutInflater)
        val bkDao = BookMarkDatabase.getInstance(requireContext()).bookMarkDao()

        // Create the ViewModel with the custom factory
        val factory = BookMarkViewModelFactory(bkDao)
        bookViewModel = ViewModelProvider(this, factory)[BookMarkViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        observe()

        onJobClick()
    }

    private fun onJobClick() {
        bkAdapter
    }

    private fun observe() {
        bookViewModel.getAllBookmark().observeForever{ bkjobs ->
            if (bkjobs != null) {
                Log.d("observe application", "Application received")
                bkAdapter.submitList(bkjobs)
            } else {
                Log.d("observe application", "No application received")
            }
            Log.d("observe application", "Application is observed")
        }
    }

    private fun prepareRecyclerView() {
        bookViewModel.getAllBookmark()
        bkAdapter = BookmarkAdapter { job ->
            val intent = Intent(activity, ActivityJob::class.java)
            intent.putExtra(JOB_ID,job.id)
            startActivity(intent)
            Log.d("Job Clicked", "Job Title: ${job.title}, Company: ")
        }

        binding.rvBookview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bkAdapter
        }
    }


}