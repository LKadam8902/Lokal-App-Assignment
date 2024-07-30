import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lokalapp.db.Job.Job
import com.example.lokalapp.db.Job.JobDao
import com.example.lokalapp.pojo.Jobs
import com.example.lokalapp.pojo.Result
import com.example.lokalapp.repository.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobRepository(private val jobDao: JobDao) {

    private val jobs = MutableLiveData<List<Result>>()

    fun getAllJobs(): MutableLiveData<List<Result>> {
        // Fetch jobs from remote API
        RetrofitInstance.jobApi.getJobsDetail().enqueue(object : Callback<Jobs> {
            override fun onResponse(call: Call<Jobs>, response: Response<Jobs>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { jobList ->
                        // Save jobs to local database
                        CoroutineScope(Dispatchers.IO).launch {
                            val jobEntities = jobList.map {
                                Job(
                                    it.id,
                                    it.title,
                                    it.primary_details?.Salary ?: "",
                                    it.primary_details?.Place ?: ""
                                )
                            }
                            jobDao.insertJobs(jobEntities)
                        }
                        // Update LiveData
                        jobs.postValue(jobList)
                    }
                    Log.d("recruiter repo", "fetched jobs")
                } else {
                    Log.d("recruiter repo error", "unable to fetch jobs")
                }
            }

            override fun onFailure(call: Call<Jobs>, t: Throwable) {
                Log.d("Repo failure", t.message.toString())
            }
        })
        return jobs
    }

    fun getJobById(jobId: Int): MutableLiveData<Job> {
        val observedJob = MutableLiveData<Job>()

        // Query local database for job details
        CoroutineScope(Dispatchers.IO).launch {
            val jobFromDb = jobDao.getJobById(jobId)
            if (jobFromDb != null) {
                observedJob.postValue(
                    Job(jobFromDb.id, jobFromDb.title, jobFromDb.Salary, jobFromDb.Location)
                )
                Log.d("job_id", "Job found with ID: $jobId")
            } else {
                Log.d("job_id", "Job not found with ID: $jobId")
            }
        }

        return observedJob
    }
}
