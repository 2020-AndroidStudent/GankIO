package com.mredrock.wanandroid.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.bean.ArticleBean;

import java.util.List;

/**
 * @author 珝珞
 * @date 2020/4/1
 * @project name WanAndroid
 *
 */

/**
 * 首页文章列表适配器
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private Context mContext;
    private List<ArticleBean> mData;
    private OnItemClickListener mListener;

    public ArticleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void refreshUi(List<ArticleBean> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_article,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ArticleBean entity = mData.get(i);
        String author = TextUtils.isEmpty(entity.getAuthor()) ? "本站发布" : entity.getAuthor();
        String title = entity.getTitle();
        String date = TextUtils.isDigitsOnly(entity.getNiceDate()) ? "未知时间" : entity.getNiceDate();
        String category = entity.getSuperChapterName();
        viewHolder.tv_author.setText(author);
        viewHolder.tv_title.setText(title);
        viewHolder.tv_date.setText(date);
        viewHolder.tv_category.setText(category);
        viewHolder.ll_root.setTag(entity);
        viewHolder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleBean _bean = (ArticleBean)v.getTag();
                if (mListener != null){
                    mListener.onItemClick(_bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_author;
        private TextView tv_title;
        private TextView tv_date;
        private TextView tv_category;
        private ImageView img_star;
        private LinearLayout ll_root;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_category = itemView.findViewById(R.id.tv_category);
            img_star = itemView.findViewById(R.id.img_star);
            ll_root = itemView.findViewById(R.id.ll_root);
        }
    }

    //自定义item点击事件
    public interface OnItemClickListener{
        void onItemClick(ArticleBean bean);
    }
}
