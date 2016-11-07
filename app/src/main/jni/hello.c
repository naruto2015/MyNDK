#include "com_gds_jniutils_JniUtils.h"
#include <stdlib.h>
#include <stdio.h>




JNIEXPORT jstring JNICALL Java_com_gds_jniutils_JniUtils_getStringFromNative
        (JNIEnv * env, jobject jobject){

    return (*env)->NewStringUTF(env,"NDK 测试成功");

}

JNIEXPORT void JNICALL Java_com_gds_jniutils_JniUtils_updateFile
        (JNIEnv * env, jobject jobject, jstring jstring){
    const char * file_path=(*env)->GetStringUTFChars(env,jstring,NULL);

    if(file_path!=NULL){
        //LOGV("from c",file_path);
    }
    FILE* file=fopen(file_path,"a+");

    if(file!=NULL){
        //LOGV("from c open file succsss");
    }

    char data[]="I am a biy";
    int count=fwrite(data,strlen(data),1,file);

    if (count>0){

    }

    if(file!=NULL){
        fclose(file);
    }


}












