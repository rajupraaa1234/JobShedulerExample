package com.example.jobshedulerexample;

import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.os.Build;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
public class MyJobShedulerClass extends JobService{

    MyAsynckTaskClass myAsynckTaskClass;

    @Override
    public boolean onStartJob(JobParameters params){
        myAsynckTaskClass=new MyAsynckTaskClass(){
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                jobFinished(params,false);
            }
        };
        myAsynckTaskClass.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params){
        myAsynckTaskClass.cancel(true);
        return false;
    }

}
