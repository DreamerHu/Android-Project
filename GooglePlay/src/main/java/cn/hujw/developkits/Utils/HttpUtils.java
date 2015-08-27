package cn.hujw.developkits.Utils;

import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 2015/8/26.
 */
public class HttpUtils {

    public static void sendHttpGet(String url){
        URL u=null;
        try {
            u=new URL(url);
            HttpURLConnection urlConnection= (HttpURLConnection) u.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            String jsonString=readStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String readStream(InputStream inputStream) {
        StringBuilder builder=new StringBuilder();
        BufferedReader bufferedReader=new
                BufferedReader(new InputStreamReader(inputStream));
        String str;
        try {
            while ((bufferedReader.readLine())!=null){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
