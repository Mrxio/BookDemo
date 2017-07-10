package com.strawberrysoft.bookdemo.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.View.BlurImageview;

import java.io.File;

/**
 * Created by Android-J on 2016/8/15.
 */
public class ImageUtils {
    public static void downAndBlurImg(String url,final ImageView imageView,final ImageView background,final Context context){
        HttpUtils httpUtils = init();
        final File file = new File(FileUtils.IMAGES+"/"+MD5Until.getMd5(url)+".jpg");
        if (file.exists()){
            Bitmap bitmap = android.graphics.BitmapFactory.decodeFile(file.getAbsolutePath());
            background.setBackgroundDrawable(BlurImageview.BlurImages(bitmap, context));
            imageView.setImageURI(Uri.fromFile(file));

        }else {
            httpUtils.download(url, file.getAbsolutePath(), new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    Bitmap bitmap = android.graphics.BitmapFactory.decodeFile(file.getAbsolutePath());
                    background.setBackgroundDrawable(BlurImageview.BlurImages(bitmap, context));
                    imageView.setImageURI(Uri.fromFile(file));
                }

                @Override
                public void onFailure(HttpException e, String s) {

                }
            });
        }
    }
    public static void showOnlineImg(String url,final ImageView imageView,final String tag){
        HttpUtils httpUtils = init();
        final File file = new File(FileUtils.IMAGES+"/"+MD5Until.getMd5(url)+".jpg");
        if (file.exists()){
            if (imageView.getTag().equals(tag)){
                imageView.setImageURI(Uri.fromFile(file));
            }
        }else {
            if (imageView.getTag().equals(tag)){
                imageView.setImageResource(R.mipmap.empty_picture);
            }
            httpUtils.download(url, file.getAbsolutePath(), new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    System.out.println("成功");
                    if (imageView.getTag().equals(tag)){
                        imageView.setImageURI(Uri.fromFile(file));
                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    System.out.println("失败");
                    if (imageView.getTag().equals(tag)){
                        imageView.setImageResource(R.mipmap.empty_picture);
                    }
                }
            });
        }
    }
    public static HttpUtils init(){
        HttpUtils httpUtils = new HttpUtils(30*1000);
        return httpUtils;
    }
}
