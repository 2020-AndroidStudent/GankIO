package com.mredrock.wanandroid.view.activity;


import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.base.BaseActivity;
import com.mredrock.wanandroid.base.BaseFragment;
import com.mredrock.wanandroid.contract.MainContract;
import com.mredrock.wanandroid.presenter.MainPresenter;
import com.mredrock.wanandroid.view.fragment.ProjectFragment;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{

    @Override
    public MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(new ProjectFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_layout_frame, fragment);
        transaction.commit();
        Log.d(TAG, "setFragment: ");
    }
}
