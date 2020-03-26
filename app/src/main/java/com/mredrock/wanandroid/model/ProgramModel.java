package com.mredrock.wanandroid.model;

import android.os.Handler;

import com.mredrock.wanandroid.base.BaseModel;
import com.mredrock.wanandroid.contract.ProgramContract;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProgramModel extends BaseModel implements ProgramContract.Model {
    public ProgramModel(Handler handler) {
        super(handler);
    }
}
