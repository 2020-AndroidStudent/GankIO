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
import com.mredrock.wanandroid.bean.Program;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder>{

    private List<Program> programList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_program, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ViewHolder holder, int position) {
        Program program = programList.get(position);
        holder.descText.setText(program.getDesc());
        holder.authorText.setText(program.getAuthor());
//        holder.avatarImage
        holder.niceShareDateText.setText(program.getNiceShareDate());
//        holder.envelopePicImage
        holder.programView.setOnClickListener(v -> {
            // 点击效果
        });
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View programView;
        TextView descText, authorText, niceShareDateText;
        ImageView avatarImage, envelopePicImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            programView = itemView;
            descText = (TextView) itemView.findViewById(R.id.text_program_desc);
            authorText = (TextView) itemView.findViewById(R.id.text_program_author);
            niceShareDateText = (TextView) itemView.findViewById(R.id.text_program_niceShareDate);
            avatarImage = (ImageView) itemView.findViewById(R.id.image_program_avatar);
            envelopePicImage = (ImageView) itemView.findViewById(R.id.image_program_envelopePic);
        }
    }

    public ProgramAdapter(Context context, List<Program> programList) {
        this.context = context;
        this.programList = programList;
    }
}
