package com.mredrock.wanandroid.model;

import android.os.Handler;
import android.os.Message;

import com.mredrock.wanandroid.base.BaseModel;
import com.mredrock.wanandroid.contract.HomeContract;
import com.mredrock.wanandroid.network.httphelper.CallBack;
import com.mredrock.wanandroid.network.httphelper.NetUtil;
import com.mredrock.wanandroid.network.httphelper.Request;

/**
 * @author 珝珞
 * @date 2020/3/26
 * @project name WanAndroid
 */
public class HomeModel extends BaseModel implements HomeContract.Model {

    public HomeModel(Handler handler) {
        super(handler);
    }

    @Override
    public void getBannerData(Request request) {
        NetUtil.getInstance().execute(request, new CallBack() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.what = 0;
                message.obj = response;
                sendMessage(message);
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void getArticleData(Request request) {
        NetUtil.getInstance().execute(request, new CallBack() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.what = 1;
                message.obj = response;
                sendMessage(message);
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
