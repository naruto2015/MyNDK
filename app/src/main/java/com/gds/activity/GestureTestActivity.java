package com.gds.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import demo.ndk.com.myndk.R;

public class GestureTestActivity extends AppCompatActivity {


    private TextView tv_testgesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_test);


        tv_testgesture= (TextView) findViewById(R.id.tv_testgesture);



    }




}
