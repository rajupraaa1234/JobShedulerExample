package com.example.jobshedulerexample;

import android.os.AsyncTask;

public class MyAsynckTaskClass extends AsyncTask<Void,Void,String>{
    @Override
    protected String doInBackground(Void... voids){
        String str="BackGround Code Running...";
        return str;
    }
}
