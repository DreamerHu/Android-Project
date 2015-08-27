package com.hujw.GooglePlay.Utils;

import android.os.Environment;

import java.io.File;
import java.util.Enumeration;

/**
 * Created by Admin on 2015/8/27.
 */
public class FileUtils {

    private static String ICON="image";
    private static String CACHE="cache";
    private static String ROOT="GooglePlay";
    public static File getCacheDir(){
        File file=getDir(CACHE);

        return file;
    }

    public static File getIconDir(){
        return getDir(ICON);
    }

    /**
     * 获取文件路径
     * @param cache
     * @return
     */
    private static File getDir(String cache) {
        StringBuilder path=new StringBuilder();
        if(isSDAvailable()){
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);
            path.append(ROOT);
            path.append(File.separator);
            path.append(cache);
        }else{
            File fileDir=UiUtils.getContext().getCacheDir();
            path.append(fileDir.getAbsolutePath());
            path.append(File.separator);
            path.append(cache);
        }
        File file=new File(path.toString());
        if(!file.isDirectory()||!file.exists()){
            file.mkdirs();
        }

        return file;

    }

    /**
     * 判断sd可是否可用
     * @return
     */
    private static boolean isSDAvailable() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }
    }


}
