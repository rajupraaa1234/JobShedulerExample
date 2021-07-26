package com.example.jobshedulerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button start;
    Button cancel;
    JobScheduler jobScheduler;
    JobInfo jobInfo;
    private static int job_id=100;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        SetupJobSheduler();
    }



    public void init(){
        start=findViewById(R.id.start);
        cancel=findViewById(R.id.cancel);
        start.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start:
                startJob();
                break;
            case R.id.cancel:
                ClearJob();
                break;
        }
    }

    private void ClearJob(){
        jobScheduler.cancel(job_id);
        Toast.makeText(this, "Stop Job Shedule", Toast.LENGTH_SHORT).show();
    }

    private void startJob() {
          jobScheduler.schedule(jobInfo);
          Toast.makeText(this, "Start Job Shedule", Toast.LENGTH_SHORT).show();
    }

    private void SetupJobSheduler(){
        ComponentName componentName=new ComponentName(this,MyJobShedulerClass.class);
        JobInfo.Builder builder=new JobInfo.Builder(job_id,componentName);

        builder.setPeriodic(2000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);

        jobInfo=builder.build();
        jobScheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
    }
}