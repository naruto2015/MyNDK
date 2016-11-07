package com.gds.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gds.myview.canvas.CheckView;

import demo.ndk.com.myndk.R;

public class TestCanvasActivity extends AppCompatActivity {



    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_canvas);


        final CheckView checkView= (CheckView) findViewById(R.id.checkview);


        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    checkView.check();
                    flag=false;
                }else{
                    checkView.unCheck();
                }
            }
        });


    }



}
