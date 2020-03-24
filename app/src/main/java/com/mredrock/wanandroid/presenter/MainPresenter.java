package com.mredrock.wanandroid.presenter;

import android.os.Handler;
import android.os.Message;

import com.mredrock.wanandroid.base.BasePresenter;
import com.mredrock.wanandroid.base.IModel;
import com.mredrock.wanandroid.base.IView;
import com.mredrock.wanandroid.contract.MainContract;
import com.mredrock.wanandroid.model.MainModel;

/**
 * @author 行云流水
 * @date 2020/3/24
 * @description
 */
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> implements MainContract.Presenter {
    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public MainContract.Model initModel(Handler handler) {
        return new MainModel(handler);
    }

    @Override
    public void modelResponse(Message msg) {

    }
}
