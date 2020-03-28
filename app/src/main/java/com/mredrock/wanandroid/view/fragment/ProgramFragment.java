package com.mredrock.wanandroid.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.base.BaseFragment;
import com.mredrock.wanandroid.bean.Program;
import com.mredrock.wanandroid.bean.ProgramType;
import com.mredrock.wanandroid.contract.ProgramContract;
import com.mredrock.wanandroid.presenter.ProgramPresenter;
import com.mredrock.wanandroid.view.adapter.ProgramAdapter;
import com.mredrock.wanandroid.view.adapter.ProgramTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/25
 * @description
 */
public class ProgramFragment extends BaseFragment<ProgramPresenter> implements ProgramContract.View {

    private RecyclerView recyclerViewProgramType, recyclerViewProgram;
    private ProgramTypeAdapter programTypeAdapter;
    private ProgramAdapter programAdapter;
    private List<ProgramType> programTypeList = new ArrayList<>();
    private List<Program> programList = new ArrayList<>();

    @Override
    public ProgramPresenter initPresenter() {
        return new ProgramPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program, container, false);
        // 项目分类横向recyclerView
        recyclerViewProgramType = (RecyclerView)view.findViewById(R.id.recyclerView_program_type);
        LinearLayoutManager layoutManagerType = new LinearLayoutManager(view.getContext());
        layoutManagerType.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewProgramType.setLayoutManager(layoutManagerType);
        recyclerViewProgramType.setAdapter(programTypeAdapter);

        // 分类下具体纵向recyclerView
        recyclerViewProgram = (RecyclerView)view.findViewById(R.id.recyclerView_program);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewProgram.setLayoutManager(layoutManager);
//        recyclerViewProgram.setAdapter();
        return view;
    }
}
