package com.hujw.GooglePlay.Procotol;

import android.util.Log;

import com.hujw.GooglePlay.domain.SubjectInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2015/8/27.
 */
public class SubjectProcotol extends BaseProcotol<List<SubjectInfo>> {
    @Override
    protected List<SubjectInfo> parseJson(String jsonString) {

        List<SubjectInfo> subjectInfos=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(jsonString);
            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                String des=jsonObject.getString("des");
                String url=jsonObject.getString("url");
                subjectInfos.add(new SubjectInfo(des,url));
            }

            return subjectInfos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getKey() {
        return "subject";
    }
}
