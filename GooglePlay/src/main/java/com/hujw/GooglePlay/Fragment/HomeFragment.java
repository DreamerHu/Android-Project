package com.hujw.GooglePlay.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hujw.GooglePlay.Activity.R;
import com.hujw.GooglePlay.CustomView.BaseListView;
import com.hujw.GooglePlay.CustomView.LoadPage;
import com.hujw.GooglePlay.Procotol.HomeProcotol;
import com.hujw.GooglePlay.Utils.HttpUtils;
import com.hujw.GooglePlay.Utils.UiUtils;
import com.hujw.GooglePlay.domain.AppInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/8/21.
 */
public class HomeFragment extends BaseFragment  {
    List<AppInfo> datas;
    @Override
    protected View createSuccessView() {
        BaseListView listView=new BaseListView(getActivity());
        listView.setAdapter(new HomeAdapter());
        return listView;
    }
    class HomeAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ViewHolder();
                convertView=UiUtils.inflater(R.layout.appinfo_item);
                viewHolder.item_icon= (ImageView) convertView.findViewById(R.id.item_icon);
                viewHolder.item_des= (TextView) convertView.findViewById(R.id.item_des);
                viewHolder.item_ratingBar= (RatingBar) convertView.findViewById(R.id.item_ratingBar);
                viewHolder.item_size= (TextView) convertView.findViewById(R.id.item_size);
                viewHolder.item_title= (TextView) convertView.findViewById(R.id.item_title);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            AppInfo appInfo=datas.get(position);
            viewHolder.item_title.setText(appInfo.getName());
            viewHolder.item_ratingBar.setRating(appInfo.getStars());
            viewHolder.item_size.setText(Formatter.formatFileSize(UiUtils.getContext(), appInfo.getSize()));
            viewHolder.item_des.setText(appInfo.getDes());
            HttpUtils.getBitmapUtils().display(viewHolder.item_icon, HttpUtils.Url+"image?name="+appInfo.getIconUrl());
            return convertView;
        }
        class ViewHolder{
            ImageView item_icon;
            TextView item_title;
            RatingBar item_ratingBar;
            TextView item_size;
            TextView item_des;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected LoadPage.LoadResult load() {

        HomeProcotol procotol=new HomeProcotol();
        datas = procotol.load(0);
        return checkedData(datas);
    }



}
