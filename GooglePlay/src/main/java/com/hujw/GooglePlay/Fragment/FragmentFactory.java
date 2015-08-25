package com.hujw.GooglePlay.Fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/21.
 */
public class FragmentFactory {
    private static Map<Integer,Fragment> fragments=new HashMap<Integer,Fragment>();

    public static Fragment createFragment(int position){

        Fragment fragment=null;
        fragment=fragments.get(position);
        if(fragment==null){
            if(position==0)
            {
                fragment=new HomeFragment();

            }
            else if(position==1)
            {
                fragment=new AppFragment();
            }
            else if(position==2)
            {
                fragment=new GameFragment();
            }
            else if(position==3)
            {
                fragment=new SubjectFragment();
            }
            else if(position==4)
            {
                fragment=new CategoryFragment();
            }
            else if(position==5)
            {
                fragment=new TopFragment();
            }
            if(fragment!=null){
                fragments.put(position,fragment);
            }
        }



        return fragment;
    }


}
