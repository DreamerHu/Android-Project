package com.hujw.GooglePlay.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hujw.GooglePlay.Activity.R;
import com.hujw.GooglePlay.CustomView.BaseListView;
import com.hujw.GooglePlay.CustomView.LoadPage;
import com.hujw.GooglePlay.Procotol.SubjectProcotol;
import com.hujw.GooglePlay.Utils.HttpUtils;
import com.hujw.GooglePlay.Utils.UiUtils;
import com.hujw.GooglePlay.domain.SubjectInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/8/21.
 */
public class SubjectFragment extends BaseFragment {
    List<SubjectInfo> datas;

    @Override
    protected View createSuccessView() {
        BaseListView listView=new BaseListView(getActivity());
        listView.setAdapter(new SubjectAdapter());
        return listView;
    }
    class SubjectAdapter extends BaseAdapter{
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
            ViewHolder viewHolder=null;
            if(convertView==null)
            {
                viewHolder=new ViewHolder();
                convertView=UiUtils.inflater(R.layout.subject_item);
                viewHolder.item_des= (TextView) convertView.findViewById(R.id.item_txt);
                viewHolder.item_icon= (ImageView) convertView.findViewById(R.id.item_icon);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            SubjectInfo subjectInfo=datas.get(position);

            viewHolder.item_des.setText(subjectInfo.getDes());
            HttpUtils.getBitmapUtils().display(viewHolder.item_icon,HttpUtils.Url+"image?name="+subjectInfo.getUrl());

            return convertView;
        }
        class ViewHolder{
            TextView item_des;
            ImageView item_icon;
        }
    }

    @Override
    protected LoadPage.LoadResult load() {
        SubjectProcotol procotol=new SubjectProcotol();
        datas=procotol.load(0);
        return checkedData(datas);
    }
}
