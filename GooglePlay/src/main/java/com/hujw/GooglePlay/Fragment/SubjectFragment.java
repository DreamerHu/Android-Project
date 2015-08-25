package com.hujw.GooglePlay.Fragment;

import android.view.View;

import com.hujw.GooglePlay.CustomView.LoadPage;

/**
 * Created by Administrator on 2015/8/21.
 */
public class SubjectFragment extends BaseFragment {


    @Override
    protected View createSuccessView() {
        return null;
    }

    @Override
    protected LoadPage.LoadResult load() {
        return LoadPage.LoadResult.Empty;
    }
}
