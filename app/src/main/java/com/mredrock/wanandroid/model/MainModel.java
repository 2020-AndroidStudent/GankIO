package com.mredrock.wanandroid.model;

import android.os.Handler;

import com.mredrock.wanandroid.base.BaseModel;
import com.mredrock.wanandroid.contract.MainContract;

/**
 * @author 行云流水
 * @date 2020/3/24
 * @description
 */
public class MainModel extends BaseModel implements MainContract.Model {
    public MainModel(Handler handler) {
        super(handler);
    }
}
