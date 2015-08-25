package com.hujw.GooglePlay.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hujw.GooglePlay.Fragment.BaseFragment;
import com.hujw.GooglePlay.Fragment.FragmentFactory;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private String[] tab_titles;
    private ViewPager mViewPager;
    private PagerTabStrip pagerTabStrip;



    protected void initActionBar() {
        setContentView(R.layout.activity_main);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.hello_world,R.string.hello_world);
        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);

    }

    @Override
    protected void initDatas() {

        tab_titles=getResources().getStringArray(R.array.TabTitles);

    }

    @Override
    protected void initComponent() {
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                BaseFragment baseFragment= (BaseFragment) FragmentFactory.createFragment(position);
                baseFragment.show();
            }
        });
        pagerTabStrip= (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTabIndicatorColorResource(R.color.IndicatorColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }
    class MainAdapter extends FragmentStatePagerAdapter{
        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tab_titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_titles[position];
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;

        }

        return drawerToggle.onOptionsItemSelected(item)|super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this,query,Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(this,newText,Toast.LENGTH_SHORT).show();
        return false;
    }
}
