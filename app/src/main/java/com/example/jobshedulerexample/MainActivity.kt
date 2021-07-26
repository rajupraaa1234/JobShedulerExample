package com.example.jobshedulerexample

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var start: Button? = null
    var cancel: Button? = null
    var jobScheduler: JobScheduler? = null
    var jobInfo: JobInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        SetupJobSheduler()
    }

    fun init() {
        start = findViewById(R.id.start)
        cancel = findViewById(R.id.cancel)
        with(start) { this?.setOnClickListener(this@MainActivity) }
        with(cancel) {this?.setOnClickListener(this@MainActivity)}
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start -> startJob()
            R.id.cancel -> ClearJob()
        }
    }

    private fun ClearJob() {
        jobScheduler!!.cancel(job_id)
        Toast.makeText(this, "Stop Job Shedule", Toast.LENGTH_SHORT).show()
    }

    private fun startJob() {
        jobScheduler!!.schedule(jobInfo!!)
        Toast.makeText(this, "Start Job Shedule", Toast.LENGTH_SHORT).show()
    }

    private fun SetupJobSheduler() {
        val componentName = ComponentName(this, MyJobShedulerClass::class.java)
        val builder = JobInfo.Builder(job_id, componentName)
        builder.setPeriodic(2000)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        builder.setPersisted(true)
        jobInfo = builder.build()
        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
    }

    companion object {
        private const val job_id = 100
    }
}