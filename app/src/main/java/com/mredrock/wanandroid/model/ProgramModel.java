package com.mredrock.wanandroid.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mredrock.wanandroid.base.BaseModel;
import com.mredrock.wanandroid.bean.Program;
import com.mredrock.wanandroid.contract.ProgramContract;
import com.mredrock.wanandroid.network.httphelper.CallBack;
import com.mredrock.wanandroid.network.httphelper.NetUtil;
import com.mredrock.wanandroid.network.httphelper.Request;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProgramModel extends BaseModel implements ProgramContract.Model {
    private static final String TAG = "ProgramModel";
    private final String getProgramTypeUrl = "https://www.wanandroid.com/project/tree/json";
    private final String getProgramUrl = "https://www.wanandroid.com/project/list/1/json?cid=";

    @Override
    public void getProgramType() {
        Request request = new Request.Builder()
                .url(getProgramTypeUrl)
                .build();
        Log.d(TAG, "getProgramType: start");
        NetUtil.getInstance().execute(request, new CallBack() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.what = 101;
                message.obj = response;
                sendMessage(message);
                Log.d("GetProgramType", "onResponse: sendMessage");
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
                Log.d("GetProgramType", "onFailed: ");
            }
        });
        Log.d(TAG, "getProgramType: end");
    }

    @Override
    public void getProgram(String id) {
        String lastUrl = "294";
        if (id != null) {
            lastUrl = id;
        }
        Request request = new Request.Builder()
                .url(getProgramUrl + lastUrl)
                .build();
        Log.d(TAG, "getProgram: start");
        NetUtil.getInstance().execute(request, new CallBack() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.what = 102;
                message.obj = response;
                sendMessage(message);
                Log.d("getProgram", "onResponse: sendMessage");
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
                Log.d("getProgram", "onFailed: ");
            }
        });
        Log.d(TAG, "getProgram: end");
    }

    public ProgramModel(Handler handler) {
        super(handler);
    }
}
