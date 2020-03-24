package com.mredrock.wanandroid.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.base.BaseActivity;
import com.mredrock.wanandroid.contract.MainContract;
import com.mredrock.wanandroid.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{

    @Override
    public MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
