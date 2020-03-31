package com.mredrock.wanandroid.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.bean.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>{

    private List<Project> projectList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.descText.setText(project.getDesc());
        holder.authorText.setText(project.getAuthor());
//        holder.avatarImage
        holder.niceShareDateText.setText(project.getNiceShareDate());
//        holder.envelopePicImage
        holder.projectView.setOnClickListener(v -> {
            // 点击效果
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View projectView;
        TextView descText, authorText, niceShareDateText;
        ImageView avatarImage, envelopePicImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectView = itemView;
            descText = (TextView) itemView.findViewById(R.id.text_project_desc);
            authorText = (TextView) itemView.findViewById(R.id.text_project_author);
            niceShareDateText = (TextView) itemView.findViewById(R.id.text_project_niceShareDate);
            avatarImage = (ImageView) itemView.findViewById(R.id.image_project_avatar);
            envelopePicImage = (ImageView) itemView.findViewById(R.id.image_project_envelopePic);
        }
    }

    public ProjectAdapter(Context context, List<Project> projectList) {
        this.context = context;
        this.projectList = projectList;
    }
}
