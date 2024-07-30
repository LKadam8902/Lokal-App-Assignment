package com.example.lokalapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lokalapp.databinding.JobCardBinding
import com.example.lokalapp.db.Job.BookMark
import com.example.lokalapp.pojo.Result

class BookmarkAdapter(private val onItemClick: (BookMark) -> Unit) : RecyclerView.Adapter<BookmarkAdapter.JobViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<BookMark?>() {
        override fun areItemsTheSame(oldItem: BookMark, newItem: BookMark): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem:BookMark, newItem: BookMark): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = JobCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("BookmarkAdapter", "ViewHolder created")
        return JobViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val curJob = differ.currentList[position]
        Log.d("BookmarkAdapter", "Binding item at position: $position")
        holder.bind(curJob)
    }

    inner class JobViewHolder(private val binding: JobCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: BookMark?) {
            val place = job?.Location ?: "Unknown Location"
            val salary = job?.Salary ?: "Not Available"
            val title = job?.title ?: "No Title"


            Log.d("BookmarkAdapter", "Binding job: Title = $title, Place = $place, Salary = $salary")

            binding.apply {
                cardTitle.text = title
                cardSalary.text = salary
                cardLocation.text = place
            }

            binding.root.setOnClickListener {
                onItemClick(job!!)
            }

            Log.d("BookmarkAdapter", "Job bound: Title = $title, Location = ${binding.cardLocation.text}, Salary = ${binding.cardSalary.text}")
        }
    }

    fun submitList(list: List<BookMark?>) {
        differ.submitList(list)
    }
}
