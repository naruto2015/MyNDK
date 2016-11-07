package com.gds.jniutils;

/**
 * Created by naruto on 2016/10/11.
 */
public class JniUtils {

    static {
        System.loadLibrary("MyJni");//导入生成的链接库文件
    }
    public native String getStringFromNative();//本地方法

    public native void updateFile(String path);




}
