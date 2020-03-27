package com.mredrock.wanandroid.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.bean.LooperItem;
import com.mredrock.wanandroid.view.widget.MyLooperView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 珝珞
 * @date 2020/3/24
 */

public class HomeFragment extends Fragment {

    private List<LooperItem> mData;
    private MyLooperView mLooperView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        initTestData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mLooperView = view.findViewById(R.id.sob_looper);
        mLooperView.setData(new MyLooperView.InnerPageAdapter() {
            @Override
            public int getDataSize() {
                return mData.size();
            }

            @Override
            protected View getItemView(ViewGroup container,int itemPosition) {
                ImageView imageView = new ImageView(container.getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //设置图片
                imageView.setImageResource(mData.get(itemPosition).getPicResId());
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(layoutParams);
                return imageView;
            }
        },new MyLooperView.TitleBindListener() {
            @Override
            public String getTitle(int position) {
                return mData.get(position).getTitle();
            }
        });
    }

    private void initTestData() {
        mData = new ArrayList<>();
        mData.add(new LooperItem("图片1的标题",R.drawable.p1));
        mData.add(new LooperItem("图片2的标题",R.drawable.p2));
        mData.add(new LooperItem("图片3的标题",R.drawable.p3));
        mData.add(new LooperItem("图片4的标题",R.drawable.p4));
    }
}

