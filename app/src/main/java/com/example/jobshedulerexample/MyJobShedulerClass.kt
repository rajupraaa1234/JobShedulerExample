package com.example.jobshedulerexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

class MyJobShedulerClass : JobService() {
    var myAsynckTaskClass: MyAsynckTaskClass? = null
    override fun onStartJob(params: JobParameters): Boolean {
        myAsynckTaskClass = object : MyAsynckTaskClass() {
            override fun onPostExecute(s: String) {
                super.onPostExecute(s)
                Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
                jobFinished(params, false)
            }
        }
        (myAsynckTaskClass as MyAsynckTaskClass).execute()
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        myAsynckTaskClass!!.cancel(true)
        return false
    }
}