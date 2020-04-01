package com.mredrock.wanandroid.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mredrock.wanandroid.base.BasePresenter;
import com.mredrock.wanandroid.bean.Project;
import com.mredrock.wanandroid.bean.ProjectType;
import com.mredrock.wanandroid.contract.ProjectContract;
import com.mredrock.wanandroid.model.ProjectModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.Model, ProjectContract.View> implements ProjectContract.Presenter {
    private final static String TAG = "ProjectPresenter";
    private List<ProjectType> projectTypeList = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();
    public ProjectPresenter(ProjectContract.View view) {
        super(view);
    }

    @Override
    public void getProjectType() {
        mModel.getProjectType();
    }

    @Override
    public void refresh(String id) {
        mModel.refresh(id);
    }

    @Override
    public void addNewPage(String id, int page) {
        mModel.addNewPage(id, page);
    }

    @Override
    public ProjectContract.Model initModel(Handler handler) {
        return new ProjectModel(handler);
    }

    @Override
    public void getProject(String id) {
        mModel.getProject(id);
    }

    @Override
    public void modelResponse(Message msg) {
        switch (msg.what) {
            // getProjectType()返回的数据
            case 101:
                // JSON解析
                parseProjectType(msg.obj.toString());
                break;
            // getProject()返回的数据
            case 102:
                // JSON解析
                parseProject(msg.obj.toString());
                break;
            // refresh()返回的数据
            case 103:
                // JSON解析
                parseRefresh(msg.obj.toString());
                break;
            case 104:
                // JSON解析
                parseAddPage(msg.obj.toString());
        }
    }

    private void parseProjectType(String response) {
        try {
            JSONArray jsonArray = new JSONArray(new JSONObject(response).get("data").toString());
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                final String id = jsonObject.getString("id");
                Log.d(TAG, "parseProjectType: " + id);
                final String name = jsonObject.getString("name");
                Log.d(TAG, "parseProjectType: " + name);
                ProjectType projectType = new ProjectType(id, name);
                mView.getProjectTypeResponse(projectType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseProject(String response) {
        try {
            JSONArray jsonArray = new JSONArray(new JSONObject(response).getJSONObject("data").get("datas").toString());
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                final String author = jsonObject.getString("author");
                final String desc = jsonObject.getString("desc");
                final String envelopePic = jsonObject.getString("envelopePic");
                final String link = jsonObject.getString("link");
                final String niceDate = jsonObject.getString("niceDate");
//                Log.d(TAG, "parseProject: " + author);
//                Log.d(TAG, "parseProject: " + desc);
//                Log.d(TAG, "parseProject: " + envelopePic);
//                Log.d(TAG, "parseProject: " + link);
//                Log.d(TAG, "parseProject: " + niceDate);
                Project project = new Project(author, desc, envelopePic, link, niceDate);
                mView.getProjectResponse(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseRefresh(String response) {
        try {
            JSONArray jsonArray = new JSONArray(new JSONObject(response).getJSONObject("data").get("datas").toString());
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                final String author = jsonObject.getString("author");
                final String desc = jsonObject.getString("desc");
                final String envelopePic = jsonObject.getString("envelopePic");
                final String link = jsonObject.getString("link");
                final String niceDate = jsonObject.getString("niceDate");
                Log.d(TAG, "parseProject: " + author);
                Log.d(TAG, "parseProject: " + desc);
                Log.d(TAG, "parseProject: " + envelopePic);
                Log.d(TAG, "parseProject: " + link);
                Log.d(TAG, "parseProject: " + niceDate);
                Project project = new Project(author, desc, envelopePic, link, niceDate);
                if (index == jsonArray.length() - 1) {
                    mView.getRefreshResponse(project, true);
                } else {
                    mView.getRefreshResponse(project, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void parseAddPage(String response) {
        try {
            JSONArray jsonArray = new JSONArray(new JSONObject(response).getJSONObject("data").get("datas").toString());
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                final String author = jsonObject.getString("author");
                final String desc = jsonObject.getString("desc");
                final String envelopePic = jsonObject.getString("envelopePic");
                final String link = jsonObject.getString("link");
                final String niceDate = jsonObject.getString("niceDate");
                Log.d(TAG, "parseProject: " + author);
                Log.d(TAG, "parseProject: " + desc);
                Log.d(TAG, "parseProject: " + envelopePic);
                Log.d(TAG, "parseProject: " + link);
                Log.d(TAG, "parseProject: " + niceDate);
                Project project = new Project(author, desc, envelopePic, link, niceDate);
                mView.getAddNewPageResponse(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
