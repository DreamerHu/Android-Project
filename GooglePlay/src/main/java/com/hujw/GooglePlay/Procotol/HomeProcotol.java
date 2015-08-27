package com.hujw.GooglePlay.Procotol;

import com.hujw.GooglePlay.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2015/8/27.
 */
public class HomeProcotol extends BaseProcotol<List<AppInfo>> {
    @Override
    protected List<AppInfo> parseJson(String jsonString) {
        List<AppInfo> appInfos=new ArrayList<AppInfo>();
         try {
            JSONObject jsonObject=new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jObj=jsonArray.getJSONObject(i);
                long id=jObj.getLong("id");
                String name=jObj.getString("name");
                String packageName=jObj.getString("packageName");
                String iconUrl=jObj.getString("iconUrl");
                float stars=Float.parseFloat(jObj.getString("stars"));
                long size=jObj.getLong("size");
                String downloadUrl=jObj.getString("downloadUrl");
                String des=jObj.getString("des");
                appInfos.add(new AppInfo(id,name,packageName,iconUrl,stars,size,downloadUrl,des));
            }

             return appInfos;

        } catch (Exception e) {
            e.printStackTrace();
             return null;
        }
    }

    @Override
    public String getKey() {
        return "home";
    }
}
