package com.gds.myview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gaodesong on 16/10/31.
 */

public class BaseCanvasView extends View {
    public BaseCanvasView(Context context) {
        super(context);
        init();
    }

    public BaseCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setFilterBitmap(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setColor(Color.BLACK);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);
//        canvas.scale(0.5f,0.5f);
//
//        canvas.drawCircle(0,0,100,mPaint);



        mPaint.setColor(Color.BLACK);
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle(0,0,400,mPaint);
        canvas.drawCircle(0,0,370,mPaint);
        for(int i=0;i<360;i+=10){
            canvas.drawLine(0,400,0,370,mPaint);
            canvas.rotate(10);
        }






    }













}
