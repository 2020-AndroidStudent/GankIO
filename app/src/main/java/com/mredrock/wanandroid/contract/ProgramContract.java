package com.mredrock.wanandroid.contract;

import com.mredrock.wanandroid.base.IModel;
import com.mredrock.wanandroid.base.IPresenter;
import com.mredrock.wanandroid.base.IView;
import com.mredrock.wanandroid.bean.Program;
import com.mredrock.wanandroid.bean.ProgramType;

import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public interface ProgramContract {
    interface Model extends IModel {
        // 获取项目类型数据
        void getProgramType();

        // 获取具体项目数据
        void getProgram(String id);
    }

    interface View extends IView {
        // 获取项目类型数据
        void getProgramType();
        // 接受getProgramType()返回的数据
        void getProgramTypeResponse(List<ProgramType> programTypeList);

        // 获取具体项目数据
        void getProgram(String id);
        // 接受getProgram()返回的数据
        void getProgramResponse(List<Program> programList);
    }

    interface Presenter extends IPresenter {
        // 获取项目类型数据
        void getProgramType();

        // 获取具体项目数据
        void getProgram(String id);
    }
}
