package com.changwon.wooogi.frequency;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    static FrequencyDAO dao;
    private BackPressCloseHandler backPressCloseHandler;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new FrequencyDAO(getApplicationContext(), 1);
        backPressCloseHandler = new BackPressCloseHandler(this);

        Button btn_main = (Button) findViewById(R.id.btn_main);
        Button btn_sub = (Button) findViewById(R.id.btn_sub);
        TextView textView = (TextView)findViewById(R.id.test1);
        Button menual = (Button) findViewById(R.id.menual);
        Button test_btn = (Button) findViewById(R.id.test_btn);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VolumeCheck.class);
                startActivity(intent);

            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,ResultList.class);
                startActivity(intent);

            }
        });

        menual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenualTest.class);
                startActivity(intent);
            }
        });

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dBTest.class);
                startActivity(intent);
            }
        });

        textView.setText("SAFAUD AUDIOMETRY is an application to identify an individual's hearing threshold.\nHearing threshold is the sensitivity of auditory sense to the loudness of sound.\n" +
                "Decibel(dB) is used to express the loudness of sound.");

    }

    //Back Intent Active

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }


    public class BackPressCloseHandler {
        private long backKeyPressedTime = 0;
        private Toast toast;

        private Activity activity;

        public BackPressCloseHandler(Activity context) {
            this.activity = context;
        }

        public void onBackPressed() {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showGuide();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                toast.cancel();

                Intent t = new Intent(activity, MainActivity.class);
                activity.startActivity(t);

                activity.moveTaskToBack(true);
                activity.finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }

        public void showGuide() {
            toast = Toast.makeText(activity, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
