package com.hujw.GooglePlay.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.hujw.GooglePlay.Activity.R;
import com.hujw.GooglePlay.Utils.UiUtils;

/**
 * Created by Admin on 2015/8/27.
 */
public class BaseListView extends ListView {
    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseListView(Context context) {
        super(context);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //		setSelector  点击显示的颜色
//		setCacheColorHint  拖拽的颜色
//		setDivider  每个条目的间隔	的分割线
        this.setSelector(R.drawable.nothing);  // 什么都没有
       // this.setCacheColorHint(UiUtils.getDrawalbe(R.drawable.nothing));
        this.setDivider(UiUtils.getDrawalbe(R.drawable.nothing));

    }

}
