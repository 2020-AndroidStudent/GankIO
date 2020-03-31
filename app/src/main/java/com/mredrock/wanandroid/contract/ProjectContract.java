package com.mredrock.wanandroid.contract;

import com.mredrock.wanandroid.base.IModel;
import com.mredrock.wanandroid.base.IPresenter;
import com.mredrock.wanandroid.base.IView;
import com.mredrock.wanandroid.bean.Project;
import com.mredrock.wanandroid.bean.ProjectType;

import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public interface ProjectContract {
    interface Model extends IModel {
        // 获取项目类型数据
        void getProjectType();

        // 获取具体项目数据
        void getProject(String id);
    }

    interface View extends IView {
        // 获取项目类型数据
        void getProjectType();
        // 接受getProjectType()返回的数据
        void getProjectTypeResponse(List<ProjectType> projectTypeList);

        // 获取具体项目数据
        void getProject(String id);
        // 接受getProject()返回的数据
        void getProjectResponse(List<Project> projectList);
    }

    interface Presenter extends IPresenter {
        // 获取项目类型数据
        void getProjectType();

        // 获取具体项目数据
        void getProject(String id);
    }
}
