package com.mredrock.wanandroid.presenter;

import android.os.Handler;
import android.os.Message;

import com.mredrock.wanandroid.base.BasePresenter;
import com.mredrock.wanandroid.bean.Program;
import com.mredrock.wanandroid.bean.ProgramType;
import com.mredrock.wanandroid.contract.ProgramContract;
import com.mredrock.wanandroid.model.ProgramModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProgramPresenter extends BasePresenter<ProgramContract.Model, ProgramContract.View> implements ProgramContract.Presenter {
    private List<ProgramType> programTypeList = new ArrayList<>();
    private List<Program> programList = new ArrayList<>();
    public ProgramPresenter(ProgramContract.View view) {
        super(view);
    }

    @Override
    public void getProgramType() {
        mModel.getProgramType();
    }

    @Override
    public ProgramContract.Model initModel(Handler handler) {
        return new ProgramModel(handler);
    }

    @Override
    public void getProgram(String id) {
        mModel.getProgram(id);
    }

    @Override
    public void modelResponse(Message msg) {
        switch (msg.what) {
            // getProgramType()返回的数据
            case 101:
                // JSON解析
                parseProgramType(msg.obj.toString());
                mView.getProgramTypeResponse(programTypeList);
                break;

        }
    }

    private void parseProgramType(String response) {
        try {
            JSONArray jsonArray = new JSONArray(new JSONObject(response).get("data").toString());
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                final String id = jsonObject.getString("id");
                final String name = jsonObject.getString("name");
                ProgramType programType = new ProgramType(id, name);
                programTypeList.add(programType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseProgram(String response) {
        try {
            JSONArray jsonArray = new JSONArray(new JSONObject(response).getJSONObject("data").get("datas").toString());
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                final String author = jsonObject.getString("author");
                final String desc = jsonObject.getString("desc");
                final String envelopePic = jsonObject.getString("envelopePic");
                final String link = jsonObject.getString("link");
                final String niceShareDate = jsonObject.getString("niceShareDate");
                Program program = new Program(author, desc, envelopePic, link, niceShareDate);
                programList.add(program);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
