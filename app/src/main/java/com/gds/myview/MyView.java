package com.gds.myview;

/**
 * Created by naruto on 2016/10/11.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
public class MyView extends View {

    private float rx=0;
    Paint paint=new Paint();
    private MyThread thread;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(40);
        //canvas.drawText("this is onDraw",rx,30,paint);
        canvas.drawText("this is onDraw",rx,40,paint);
       /* rx=rx+30;
        canvas.drawText("this is onDraw",rx,30,paint);*/

        if(thread==null){
            thread=new MyThread();
            thread.start();
        }

    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){
                rx+=3;
                Log.e("MyView",rx+"");
                postInvalidate();
                if (rx>getWidth()){
                    rx=0-paint.measureText("this is onDraw");
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
