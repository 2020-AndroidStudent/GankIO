package com.mredrock.wanandroid.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.bean.ProgramType;

import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class ProgramTypeAdapter extends RecyclerView.Adapter<ProgramTypeAdapter.ViewHolder> {

    private List<ProgramType> programTypeList;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_program_type, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProgramType programType = programTypeList.get(position);
        holder.programTypeName.setText(programType.getName());
        holder.programTypeView.setOnClickListener(v -> {
            // 点击效果
        });
    }

    @Override
    public int getItemCount() {
        return programTypeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View programTypeView;
        TextView programTypeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            programTypeView = itemView;
            programTypeName = (TextView)itemView.findViewById(R.id.text_program_type);
        }
    }

    public ProgramTypeAdapter(Context context, List<ProgramType> programTypeList) {
        this.context = context;
        this.programTypeList = programTypeList;
    }
}
