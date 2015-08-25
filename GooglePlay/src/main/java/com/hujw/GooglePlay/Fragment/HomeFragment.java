package com.hujw.GooglePlay.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hujw.GooglePlay.Activity.R;
import com.hujw.GooglePlay.CustomView.LoadPage;
import com.hujw.GooglePlay.Utils.ViewUtils;

/**
 * Created by Administrator on 2015/8/21.
 */
public class HomeFragment extends BaseFragment  {

    @Override
    protected View createSuccessView() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected LoadPage.LoadResult load() {
        return LoadPage.LoadResult.Error;
    }

}
