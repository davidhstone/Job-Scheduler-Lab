package com.example.davidstone.job_scheduler_lab;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;
import android.widget.Toast;

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

       // oldText.setText(DataSingleton.getInstance().getMyText());
       // newText.setText(DataSingleton.getInstance().getMyText());
       // thirdText.setText(DataSingleton.getInstance().getMyText());

        //JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(),
        //        MyJobService.class.getName())).setPeriodic(5_000)
        //        .build();

        //JobInfo jobInfo = new JobInfo().Builder(1, new ComponentName(getPackageName(),
        //        LabJobService.class.getName())).setPeriodic(5_000)
        //        .build();

        PersistableBundle bundle1 = new PersistableBundle();
        //bundle1.putString("frontman", "Chuck D");
        bundle1.putString("member", "Chuck D");
        JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                LabJobService.class.getName()))
                .setExtras(bundle1)
                .setRequiresCharging(true)
                .setPeriodic(5_000)
                .build();

        PersistableBundle bundle2 = new PersistableBundle();
        //bundle2.putString("jester", "Flava Flav");
        bundle2.putString("member", "Flava Flav");
        JobInfo jobInfo2 = new JobInfo.Builder(2, new ComponentName(getPackageName(),
                LabJobService.class.getName()))
                .setExtras(bundle2)
                .setRequiresCharging(true)
                .setPeriodic(7_000)
                .build();

        PersistableBundle bundle3 = new PersistableBundle();
        //bundle3.putString("security", "Professor Griff");
        bundle3.putString("member", "Professor Griff");
        JobInfo jobInfo3 = new JobInfo.Builder(3, new ComponentName(getPackageName(),
                LabJobService.class.getName()))
                .setExtras(bundle3)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(9_000)
                .build();


        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(jobInfo);
        jobScheduler.schedule(jobInfo2);
        jobScheduler.schedule(jobInfo3);

    }

    @Override
    public void onTextChanged(String oldTextString, String newTextString) {

        Toast.makeText(MainActivity.this, "Who we shoutin' out to?", Toast.LENGTH_SHORT).show();
        if(newText.getText().length() != 0){
            oldText.setText(oldTextString);
        }
        //if(thirdText.getText().length() != 0){
        //    newText.setText(newTextString);
        //}
        //oldText.setText(DataSingleton.getInstance().getMyText());
        newText.setText("The new member is: " + DataSingleton.getInstance().getMyText2());
        thirdText.setText(DataSingleton.getInstance().getMyText2());
    }
}
