package com.mredrock.wanandroid.presenter;

import android.os.Handler;
import android.os.Message;

import com.mredrock.wanandroid.base.BasePresenter;
import com.mredrock.wanandroid.contract.ProgramContract;
import com.mredrock.wanandroid.model.ProgramModel;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProgramPresenter extends BasePresenter<ProgramContract.Model, ProgramContract.View> implements ProgramContract.Presenter {
    public ProgramPresenter(ProgramContract.View view) {
        super(view);
    }

    @Override
    public ProgramContract.Model initModel(Handler handler) {
        return new ProgramModel(handler);
    }

    @Override
    public void modelResponse(Message msg) {

    }
}
