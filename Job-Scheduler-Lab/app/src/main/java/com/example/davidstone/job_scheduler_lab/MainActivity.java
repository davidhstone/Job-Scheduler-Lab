package com.example.davidstone.job_scheduler_lab;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DataSingleton.TextChangeListener {

    TextView oldText;
    TextView newText;
    TextView thirdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DataSingleton.getInstance().setListener(this);
        DataSingleton.getInstance().setListener(this);

        oldText = (TextView) findViewById(R.id.textview_1);
        newText = (TextView) findViewById(R.id.textview_2);
        thirdText = (TextView) findViewById(R.id.textview_3);

        //JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(),
        //        MyJobService.class.getName())).setPeriodic(5_000)
        //        .build();

        //JobInfo jobInfo = new JobInfo().Builder(1, new ComponentName(getPackageName(),
        //        LabJobService.class.getName())).setPeriodic(5_000)
        //        .build();

        PersistableBundle bundle1 = new PersistableBundle();
        bundle1.putString("name", "Chuck D");
        JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                LabJobService.class.getName()))
                .setExtras(bundle1)
                .setRequiresCharging(true)
                .setPeriodic(3_000)
                .build();

        PersistableBundle bundle2 = new PersistableBundle();
        bundle2.putString("name", "Flava Flav");
        JobInfo jobInfo2 = new JobInfo.Builder(2, new ComponentName(getPackageName(),
                LabJobService.class.getName()))
                .setExtras(bundle2)
                .setRequiresCharging(true)
                .setPeriodic(4_000)
                .build();

        PersistableBundle bundle3 = new PersistableBundle();
        bundle3.putString("name", "Professor Griff");
        JobInfo jobInfo3 = new JobInfo.Builder(3, new ComponentName(getPackageName(),
                LabJobService.class.getName()))
                .setExtras(bundle3)
                .setRequiresDeviceIdle(false)
                .setPeriodic(5_000)
                .build();


        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(jobInfo);
        jobScheduler.schedule(jobInfo2);
        jobScheduler.schedule(jobInfo3);

    }

    @Override
    public void onTextChanged(String oldTextString) {
        if(newText.getText().length() != 0){
            oldText.setText(oldTextString);
        }
        if(thirdText.getText().length() != 0){
            newText.setText(oldTextString);
        }
        newText.setText(DataSingleton.getInstance().getMyText());
        thirdText.setText(DataSingleton.getInstance().getMyText());
    }
}
