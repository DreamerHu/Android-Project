package com.hujw.GooglePlay.CustomView;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hujw.GooglePlay.Activity.R;
import com.hujw.GooglePlay.Utils.UiUtils;

import cn.hujw.developkits.Manager.ThreadManager;

/**
 * Created by Admin on 2015/8/25.
 */
public abstract class LoadPage extends FrameLayout {

    private View loadingPage;
    private View errorPage;
    private View emptyPage;
    private View successPage;

    private static int STATE_LOAD=0;
    private static int STATE_ERROR=1;
    private static int STATE_EMPTY=2;
    private static int STATE_SUCCESS=3;
    private static int STATE_UNKNOW=4;

    private  int loadState=STATE_LOAD;

    private Button reStartLoad;

    public enum LoadResult{
        Error(1),Empty(2),Success(3);
        private int value;
        LoadResult(int value) {
            this.value=value;
        }

        public int getValue() {
            return value;
        }
    }

    public LoadPage(Context context) {
        super(context);
        initView();
    }



    public LoadPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if(loadingPage==null){
            loadingPage=createView(R.layout.loadpage_loading);
            this.addView(loadingPage, new FrameLayout.LayoutParams
                    (FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if(errorPage==null){
            errorPage=createView(R.layout.loadpage_error);
            this.addView(errorPage, new FrameLayout.LayoutParams
                    (FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            reStartLoad= (Button) errorPage.findViewById(R.id.btn_restartLoad);
            reStartLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show();
                }
            });
        }
        if(emptyPage==null){
            emptyPage=createView(R.layout.loadpage_empty);
            this.addView(emptyPage, new FrameLayout.LayoutParams
                    (FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        showPage();
    }

    private void showPage() {
        if(loadingPage!=null){
            loadingPage.setVisibility(loadState==STATE_LOAD||loadState==STATE_UNKNOW?View.VISIBLE:View.INVISIBLE);
        }
        if(emptyPage!=null){
            emptyPage.setVisibility(loadState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if(errorPage!=null){
            errorPage.setVisibility(loadState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if(loadState==STATE_SUCCESS){
            if(successPage==null){
                successPage=createSuccess();
                this.addView(successPage,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            }
            successPage.setVisibility(View.VISIBLE);
        }else {
            if(successPage!=null) {
                successPage.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 创建加载成功页面，返回一个成功页面的布局
     * @return
     */
    public abstract View createSuccess();

    //创建页面的方法
    private View createView(int viewId) {

        return View.inflate(UiUtils.getContext(),viewId,null);

    }
    public  void show(){

        if(loadState==STATE_ERROR||loadState==STATE_EMPTY){
            loadState=STATE_LOAD;
        }
        ThreadManager.getInstance().createShortPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult loadResult=load();
                if(getContext()!=null) {
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (loadResult != null) {
                                loadState = loadResult.getValue();
                                showPage();
                            }
                        }
                    });
                }
            }
        });
        showPage();
    }

    protected abstract LoadResult load();



}
