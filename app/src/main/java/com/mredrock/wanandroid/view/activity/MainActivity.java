package com.mredrock.wanandroid.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.adapter.ViewPageAdaptor;
import com.mredrock.wanandroid.base.BaseActivity;
import com.mredrock.wanandroid.contract.MainContract;
import com.mredrock.wanandroid.presenter.MainPresenter;
import com.mredrock.wanandroid.view.fragment.HomeFragment;

public class MainActivity extends  BaseActivity<MainContract.Presenter> implements MainContract.View{
    private ViewPager viewPager;
    private MenuItem menuItem;
    //        设置滑动切换页面
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            viewPager.setCurrentItem(0);
                            return true;
                        case R.id.navigation_classify:
                            viewPager.setCurrentItem(1);
                            return true;
                        case R.id.navigation_project:
                            viewPager.setCurrentItem(2);
                            return true;
                        case R.id.navigation_my:
                            viewPager.setCurrentItem(3);
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.vp1);
        final BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        //让fragment与下面栏关联
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null)
                    menuItem.setChecked(false);
                else navigation.getMenu().getItem(0).setChecked(false);
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    //添加fragment
    private void setupViewPager(ViewPager viewPager) {
        HomeFragment homeFragment = new HomeFragment();
        /*在这里创建Fragment。。*/



        ViewPageAdaptor adaptor = new ViewPageAdaptor(getSupportFragmentManager());
        adaptor.addFragment(homeFragment);
        /*在这里添加Fragment。。*/

//        adaptor.addFragment(myFragment);
        viewPager.setAdapter(adaptor);
    }

}

