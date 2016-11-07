package com.gds.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by naruto on 2016/9/9.
 *
 * 当调用bitmap.compress(CompressFormat.JPEG, 100, fos);保存为图片时发现图片背景为黑色
 * 这时只需要改成用png保存就可以了，bitmap.compress(CompressFormat.PNG, 100, fos)
 */
public class BitmapFactoryUtils {

    /*
    图片尺寸压缩
     */
    public static void compressPicture(String srcPath, String desPath) {
        FileOutputStream fos = null;
        BitmapFactory.Options op = new BitmapFactory.Options();

        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        op.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);
        op.inJustDecodeBounds = false;

        // 缩放图片的尺寸
        float w = op.outWidth;
        float h = op.outHeight;
        float hh = 1024f;//
        float ww = 1024f;//
        // 最长宽度或高度1024
        float be = 1.0f;
        if (w > h && w > ww) {
            be = (float) (w / ww);
        } else if (w < h && h > hh) {
            be = (float) (h / hh);
        }
        if (be <= 0) {
            be = 1.0f;
        }
        op.inSampleSize = (int) be;// 设置缩放比例,这个数字越大,图片大小越小.
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, op);
        int desWidth = (int) (w / be);
        int desHeight = (int) (h / be);
        bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);
        try {
            fos = new FileOutputStream(desPath);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
    图片质量压缩
     */

    public static void compressBmpToFile(Bitmap bmp, File file){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int options=80;//个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG,options,baos);
        while(baos.toByteArray().length/1024>100){
            baos.reset();
            options-=10;
            bmp.compress(Bitmap.CompressFormat.JPEG,options,baos);
        }

        try {
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
