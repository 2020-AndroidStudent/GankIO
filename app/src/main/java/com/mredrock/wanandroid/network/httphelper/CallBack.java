package com.mredrock.wanandroid.network.httphelper;

public interface CallBack {

    void onResponse(String response);

    void onFailed(Exception e);
}
