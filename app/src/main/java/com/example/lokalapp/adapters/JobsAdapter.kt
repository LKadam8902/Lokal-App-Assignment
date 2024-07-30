package com.example.lokalapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lokalapp.databinding.JobCardBinding
import com.example.lokalapp.pojo.Result

class JobsAdapter(private val onItemClick: (Result) -> Unit) : RecyclerView.Adapter<JobsAdapter.JobViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = JobCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("JobsAdapter", "onCreateViewHolder called")
        return JobViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val curJob = differ.currentList[position]
        Log.d("JobsAdapter", "onBindViewHolder called for position: $position")
        holder.bind(curJob)
    }

    inner class JobViewHolder(private val binding: JobCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: Result) {
            var place: String? = job.primary_details?.Place?.toString()
            var salary: String? = job.primary_details?.Salary
            val title = job.title ?: "No Title"
            val whatsappNo = job.whatsapp_no ?: "No Phone"

            Log.d("JobsAdapter", "Binding job: $title, $place, $salary, $whatsappNo")

            binding.apply {
                cardTitle.text = title
                cardSalary.text = salary
                cardLocation.text = place
                cardPhone.text = whatsappNo
            }

            binding.root.setOnClickListener {
                onItemClick(job)
            }

            Log.d("JobsAdapter", "Binded job: $title, ${binding.cardLocation.text}, ${binding.cardSalary.text}, $whatsappNo")
        }
    }

    fun submitList(list: List<Result>) {
        differ.submitList(list)
    }
}
