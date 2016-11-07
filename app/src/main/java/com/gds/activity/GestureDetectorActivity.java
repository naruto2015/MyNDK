package com.gds.activity;

import android.nfc.Tag;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import demo.ndk.com.myndk.R;

public class GestureDetectorActivity extends AppCompatActivity  {


    private static final String TAG="gestures";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);

        mDetector=new GestureDetectorCompat(this,new MyGestureListener());


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            //down事件

            return super.onDown(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e(TAG,"onSingleTapUp"+e.getAction());
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //滑动手势事件；Touch了滑动一点距离后，在ACTION_UP时才会触发

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //在双击的第二下，Touch down时触发

            return super.onDoubleTap(e);
        }

        @Override
        public void onShowPress(MotionEvent e) {

            super.onShowPress(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            Log.e(TAG,e1.getX()+";"+e1.getY()+";"+e2.getX()+";"+e2.getY());

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }




}
