package com.hujw.GooglePlay.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hujw.GooglePlay.Activity.R;
import com.hujw.GooglePlay.CustomView.LoadPage;
import com.hujw.GooglePlay.Utils.ViewUtils;

/**
 * Created by Admin on 2015/8/25.
 */
public abstract class BaseFragment extends Fragment {

    private LoadPage loadFrameLayout;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //初始化FrameLayout状态界面
        initView();

        //显示对应状态的界面
        return loadFrameLayout;
    }

    private void initView() {
        if(loadFrameLayout==null){
            loadFrameLayout=new LoadPage(getActivity()) {
                @Override
                public View createSuccess() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                protected LoadResult load() {
                    return BaseFragment.this.load();
                }
            };
        }else {
            ViewUtils.removeParentView(loadFrameLayout);
        }

    }
    /**
     * 创建加载成功页面，返回一个成功页面的布局
     * @return
     */
    protected abstract View createSuccessView();





    protected abstract LoadPage.LoadResult load();

    public void show() {
        if(loadFrameLayout!=null){
            loadFrameLayout.show();
        }
    }
}
