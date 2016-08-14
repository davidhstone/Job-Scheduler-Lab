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
        final String memberName = bundle.getString("name");

        mTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... string) {

            //    Calendar calendar = Calendar.getInstance();
            //    calendar.setTimeInMillis(System.currentTimeMillis());
            //    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            //    String newText = "New Text " + timeFormat.format(calendar.getTime());

                return string[0];
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                DataSingleton.getInstance().updateMyText(string);
                jobFinished(jobParameters, false);
            }
        };
        mTask.execute(memberName);
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
