package com.mredrock.wanandroid.network.httphelper;

/**
 * @author 行云流水
 * @date 2020/3/24
 * @description
 */
public interface CallBack {

    void onResponse(String response);

    void onFailed(Exception e);
}
