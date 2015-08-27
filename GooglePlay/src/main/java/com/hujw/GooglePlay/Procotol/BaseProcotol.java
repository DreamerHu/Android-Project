package com.hujw.GooglePlay.Procotol;

import android.util.Log;

import com.hujw.GooglePlay.Utils.FileUtils;
import com.hujw.GooglePlay.Utils.HttpUtils;
import com.lidroid.xutils.util.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Admin on 2015/8/27.
 */
public abstract class BaseProcotol <T>{

    public T load(int index){

        String jsonString=loadLocal(index);
        if(jsonString==null){
            jsonString=loadService(index);
            if(jsonString!=null){
                saveLocal(jsonString,index);
            }
        }

        if(jsonString!=null){
            return parseJson(jsonString);
        }else {
            return null;
        }


    }

    /**
     * 解析Json字符串
     * @param jsonString
     * @return
     */
    protected abstract T parseJson(String jsonString);
    /**
     * 保存到本地
     * @param jsonString
     * @param index
     */
    private void saveLocal(String jsonString, int index) {
        File fileDir=FileUtils.getCacheDir();
        File file=new File(fileDir,getKey()+"_"+index);
        BufferedWriter bw=null;
        try {
            FileWriter fileWriter=new FileWriter(file);
            bw=new BufferedWriter(fileWriter);
            bw.write(System.currentTimeMillis()*1000*100+"");
            bw.newLine();
            bw.write(jsonString);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(bw);
        }
    }

    /**
     * 加载服务器数据
     * @param index
     * @return
     */
    private String loadService(int index) {
        String json= HttpUtils.sendGetPost("http://127.0.0.1:8090/"+getKey()+"?index="+index);
        return json;
    }

    /**
     * 加载本地缓存数据
     * @param index 缓存数据的下标
     * @return 返回一个json字符串
     */
    private String loadLocal(int index) {
        File fileDir= FileUtils.getCacheDir();
        File file=new File(fileDir,getKey()+"_"+index);
        FileReader fileReader;
        BufferedReader br = null;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String passString=br.readLine();
            long outOfDate=Long.parseLong(passString);
            if(System.currentTimeMillis()>outOfDate){
                return null;
            }else {
                StringWriter sw=new StringWriter();
                String str="";
                while ((str=br.readLine())!=null) {
                    sw.write(str);
                }
                return sw.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            IOUtils.closeQuietly(br);
        }

    }

    public abstract String getKey();

}
