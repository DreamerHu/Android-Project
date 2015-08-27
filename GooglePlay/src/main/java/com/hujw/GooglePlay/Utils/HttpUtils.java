package com.hujw.GooglePlay.Utils;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Admin on 2015/8/27.
 */
public class HttpUtils {
    public static String Url="http://127.0.0.1:8090/";
    public static String sendGetPost(String path){
        BufferedReader br=null;
        try {
            URL url=new URL(path);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            br=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString=new StringBuilder();
            String str="";
            while ((str=br.readLine())!=null){
                jsonString.append(str);
            }
            return jsonString.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            IOUtils.closeQuietly(br);
        }

    }
    private static BitmapUtils bitmapUtils;
    public static BitmapUtils getBitmapUtils() {
        if (bitmapUtils == null) {
            // 第二个参数 缓存图片的路径 // 加载图片 最多消耗多少比例的内存 0.05-0.8f
            bitmapUtils = new BitmapUtils(UiUtils.getContext(), FileUtils
                    .getIconDir().getAbsolutePath(), 0.3f);
        }
        return bitmapUtils;
    }

}
