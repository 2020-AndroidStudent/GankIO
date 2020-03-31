package com.mredrock.wanandroid.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mredrock.wanandroid.base.BaseModel;
import com.mredrock.wanandroid.contract.ProjectContract;
import com.mredrock.wanandroid.network.httphelper.CallBack;
import com.mredrock.wanandroid.network.httphelper.NetUtil;
import com.mredrock.wanandroid.network.httphelper.Request;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProjectModel extends BaseModel implements ProjectContract.Model {
    private static final String TAG = "ProjectModel";
    private final String getProgramTypeUrl = "https://www.wanandroid.com/project/tree/json";
    private final String getProgramUrl = "https://www.wanandroid.com/project/list/1/json?cid=";

    @Override
    public void getProjectType() {
        Request request = new Request.Builder()
                .url(getProgramTypeUrl)
                .build();
        Log.d(TAG, "getProjectType: start");
        NetUtil.getInstance().execute(request, new CallBack() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.what = 101;
                message.obj = response;
                sendMessage(message);
                Log.d("GetProjectType", "onResponse: sendMessage");
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
                Log.d("GetProjectType", "onFailed: ");
            }
        });
        Log.d(TAG, "getProjectType: end");
    }

    @Override
    public void getProject(String id) {
        String lastUrl = "294";
        if (id != null) {
            lastUrl = id;
        }
        Request request = new Request.Builder()
                .url(getProgramUrl + lastUrl)
                .build();
        Log.d(TAG, "getProject: start");
        NetUtil.getInstance().execute(request, new CallBack() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.what = 102;
                message.obj = response;
                sendMessage(message);
                Log.d("getProject", "onResponse: sendMessage");
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
                Log.d("getProject", "onFailed: ");
            }
        });
        Log.d(TAG, "getProject: end");
    }

    public ProjectModel(Handler handler) {
        super(handler);
    }
}
