package com.mredrock.wanandroid.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.bean.ProjectType;

import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class ProjectTypeAdapter extends RecyclerView.Adapter<ProjectTypeAdapter.ViewHolder> {

    private List<ProjectType> projectTypeList;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project_type, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProjectType projectType = projectTypeList.get(position);
        holder.projectTypeName.setText(projectType.getName());
        holder.projectTypeView.setOnClickListener(v -> {
            // 点击效果
            

        });
    }

    @Override
    public int getItemCount() {
        return projectTypeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View projectTypeView;
        TextView projectTypeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTypeView = itemView;
            projectTypeName = (TextView)itemView.findViewById(R.id.text_project_type);
        }
    }

    public ProjectTypeAdapter(Context context, List<ProjectType> projectTypeList) {
        this.context = context;
        this.projectTypeList = projectTypeList;
    }
}
