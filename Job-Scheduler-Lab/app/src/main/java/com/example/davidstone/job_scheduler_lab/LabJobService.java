package com.example.davidstone.job_scheduler_lab;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.PersistableBundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by davidstone on 8/12/16.
 */
public class LabJobService extends JobService {

    private AsyncTask<String, Void, String> mTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        PersistableBundle bundle = jobParameters.getExtras();
        final String member = bundle.getString("member");

        //final String chuck = bundle.getString("frontman");
        //final String flava = bundle.getString("jester");
        //final String griff = bundle.getString("security");

        mTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... string) {

                String newText = member;

            //   String oldText = "The man on the mic " + chuck;
            //   String newText = "Shoutout to " + flava;
            //   String thirdText = "The S1Ws " + griff;
            //    return string[0];

                return newText;

            //    return oldText + newText + thirdText;
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                DataSingleton.getInstance().updateMyText(string, string);
                jobFinished(jobParameters, false);
            }
        };
        //mTask.execute(memberName);
        mTask.execute(member);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        if (mTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
            mTask.cancel(false);
        }
        return false;
    }
}
