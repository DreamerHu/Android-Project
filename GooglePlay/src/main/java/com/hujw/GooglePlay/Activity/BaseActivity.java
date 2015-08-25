package com.hujw.GooglePlay.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2015/8/21.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
        initActionBar();
        initComponent();
        
    }

    /**
     * 初始化组件
     */
    protected void initComponent() {

    }

    /**
     * 初始化数据
     */
    protected void initDatas() {
    }

    /**
     *初始化ActionBar
     */
    protected void initActionBar() {
        
    }
}
