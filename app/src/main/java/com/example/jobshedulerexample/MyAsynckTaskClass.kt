package com.example.jobshedulerexample

import android.os.AsyncTask

open class MyAsynckTaskClass : AsyncTask<Void?, Void?, String>() {
    protected override fun doInBackground(vararg p0: Void?): String? {
        return "BackGround Code Running..."
    }
}