package com.mredrock.wanandroid.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.base.BaseFragment;
import com.mredrock.wanandroid.bean.Project;
import com.mredrock.wanandroid.bean.ProjectType;
import com.mredrock.wanandroid.contract.ProjectContract;
import com.mredrock.wanandroid.presenter.ProjectPresenter;
import com.mredrock.wanandroid.view.adapter.ProjectAdapter;
import com.mredrock.wanandroid.view.adapter.ProjectTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    private RecyclerView recyclerViewProjectType, recyclerViewProject;
    private ProjectTypeAdapter projectTypeAdapter;
    private ProjectAdapter projectAdapter;


    private List<ProjectType> projectTypeList = new ArrayList<>();

    @Override
    public void getProjectTypeResponse(List<ProjectType> projectTypeList) {
        this.projectTypeList = projectTypeList;
        projectTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void getProjectType() {
        mPresenter.getProjectType();
    }


    private List<Project> projectList = new ArrayList<>();

    @Override
    public ProjectPresenter initPresenter() {
        return new ProjectPresenter(this);
    }

    @Override
    public void getProjectResponse(List<Project> projectList) {
        this.projectList = projectList;
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void getProject(String id) {
        mPresenter.getProject(id);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_fragment, container, false);
        getProjectType();
        // 项目分类横向recyclerView
        recyclerViewProjectType = (RecyclerView)view.findViewById(R.id.recyclerView_project_type);
        LinearLayoutManager layoutManagerType = new LinearLayoutManager(view.getContext());
        layoutManagerType.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewProjectType.setLayoutManager(layoutManagerType);
        projectTypeAdapter = new ProjectTypeAdapter(view.getContext(), projectTypeList);
        recyclerViewProjectType.setAdapter(projectTypeAdapter);

        getProject(null);
        // 分类下具体纵向recyclerView
        recyclerViewProject = (RecyclerView)view.findViewById(R.id.recyclerView_project);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewProject.setLayoutManager(layoutManager);
        projectAdapter = new ProjectAdapter(view.getContext(), projectList);
        recyclerViewProject.setAdapter(projectAdapter);
        return view;
    }
}
