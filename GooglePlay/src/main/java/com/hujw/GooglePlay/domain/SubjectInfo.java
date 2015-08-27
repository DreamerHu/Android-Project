package com.hujw.GooglePlay.domain;

/**
 * Created by Admin on 2015/8/27.
 */
public class SubjectInfo {
    private String des;
    private String url;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SubjectInfo(String des, String url) {
        this.des = des;
        this.url = url;
    }

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "des='" + des + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
