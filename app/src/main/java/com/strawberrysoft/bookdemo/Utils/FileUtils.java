package com.strawberrysoft.bookdemo.Utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Android-J on 2016/8/15.
 */
public class FileUtils {
    public static String ExternalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String APPROOT = ExternalStorageDirectory+"/BookDemo";
    public static String IMAGES = APPROOT+"/images";
    public static void initFolders(){
        System.out.println(ExternalStorageDirectory);
        System.out.println(APPROOT);
        System.out.println(IMAGES);
        File file = new File(APPROOT);
        File file1 = new File(IMAGES);
//        if (!file.exists()){
            file.mkdirs();
//        }
//        if (!file1.exists()){
            file1.mkdirs();
//        }
    }
}
