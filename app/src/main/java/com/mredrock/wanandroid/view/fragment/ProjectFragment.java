package com.mredrock.wanandroid.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.base.BaseFragment;
import com.mredrock.wanandroid.bean.Project;
import com.mredrock.wanandroid.bean.ProjectType;
import com.mredrock.wanandroid.contract.ProjectContract;
import com.mredrock.wanandroid.presenter.ProjectPresenter;
import com.mredrock.wanandroid.uitls.SevenUtil;
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
    private SwipeRefreshLayout swipeRefresh;
    private String id = null;
    private int page = 2;
    private List<ProjectType> projectTypeList = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_fragment, container, false);
        swipeRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorRed);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(id);
            }
        });
        getProjectType();
        Log.d(TAG, "onCreateView: getProjectType");
        // 项目分类横向recyclerView
        recyclerViewProjectType = (RecyclerView)view.findViewById(R.id.recyclerView_project_type);
        LinearLayoutManager layoutManagerType = new LinearLayoutManager(view.getContext());
        layoutManagerType.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewProjectType.setLayoutManager(layoutManagerType);
        projectTypeAdapter = new ProjectTypeAdapter(getContext(), projectTypeList);
        recyclerViewProjectType.setAdapter(projectTypeAdapter);

        getProject(null);
        // 分类下具体纵向recyclerView
        recyclerViewProject = (RecyclerView)view.findViewById(R.id.recyclerView_project);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewProject.setLayoutManager(layoutManager);
        projectAdapter = new ProjectAdapter(getContext(), projectList);
        recyclerViewProject.setAdapter(projectAdapter);
        recyclerViewProject.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(SevenUtil.isSlideToBottom(recyclerViewProject)) {
                    addNewPage(id, page);
                }
            }
        });
        return view;
    }

    @Override
    public ProjectPresenter initPresenter() {
        return new ProjectPresenter(this);
    }

    @Override
    public void getProject(String id) {
        projectList.clear();
        mPresenter.getProject(id);
    }

    @Override
    public void getProjectResponse(Project project) {
        this.projectList.add(project);
        projectAdapter.notifyDataSetChanged();
        Log.d(TAG, "getProjectResponse: succeed");
    }

    @Override
    public void getProjectType() {
        projectTypeList.clear();
        mPresenter.getProjectType();
    }

    @Override
    public void getProjectTypeResponse(ProjectType projectType) {
        this.projectTypeList.add(projectType);
        projectTypeAdapter.notifyDataSetChanged();
        Log.d(TAG, "getProjectTypeResponse: succeed");
    }

    @Override
    public void refresh(String id) {
        page = 2;
        projectList.clear();
        mPresenter.refresh(id);
    }

    @Override
    public void getRefreshResponse(Project project, Boolean isOff) {
        if (isOff) {
            swipeRefresh.setRefreshing(false);
        }
        projectList.add(project);
        projectAdapter.notifyDataSetChanged();
        Log.d(TAG, "getRefreshResponse: succeed");
    }

    @Override
    public void addNewPage(String id, int page) {
        mPresenter.addNewPage(id, page);
    }

    @Override
    public void getAddNewPageResponse(Project project) {
        projectList.add(project);
        projectAdapter.notifyDataSetChanged();
        Log.d(TAG, "getAddNewPageResponse: succeed");
    }
}
