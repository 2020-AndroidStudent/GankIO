package com.mredrock.wanandroid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.adapter.ArticleAdapter;
import com.mredrock.wanandroid.base.BaseFragment;
import com.mredrock.wanandroid.bean.ArticleBean;
import com.mredrock.wanandroid.bean.BannerBean;
import com.mredrock.wanandroid.contract.HomeContract;
import com.mredrock.wanandroid.network.httphelper.Request;
import com.mredrock.wanandroid.presenter.HomePresenter;
import com.mredrock.wanandroid.view.activity.ArticleInfoActivity;
import com.mredrock.wanandroid.view.widget.MyLooperView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 珝珞
 * @date 2020/3/24
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {
    private MyLooperView mLooperView;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;//文章列表适配器
    private int mPageId = 0;//页码
    private boolean isRefresh = false;//标记是否触发下拉刷新
    private List<ArticleBean> mArticleList = new ArrayList<>();
    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        mLooperView = view.findViewById(R.id.sob_looper);
        mRefreshLayout = view.findViewById(R.id.home_refresh_list);
        mRecyclerView = view.findViewById(R.id.home_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ArticleAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        //下拉刷新控件设置事件
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                mPageId = 0;
                mArticleList.clear();
                getArticleData();
            }
        });

        //item 点击事件
        mAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ArticleBean bean) {
                String url = bean.getLink();
                if (TextUtils.isEmpty(url)){
                    Toast.makeText(getActivity(),"链接不可用",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(getActivity(), ArticleInfoActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }

    public void initData() {
        //获取banner数据
        getBannerData();
        //获取文章数据
        getArticleData();
    }


    @Override
    public void onBannerData(List<BannerBean> data) {
        if (data != null && data.size() > 0){
            mLooperView.refreshUi(data);
        }
    }

    @Override
    public void onArticleData(List<ArticleBean> data) {
        if (data != null && data.size() > 0){
            mArticleList.addAll(data);
            mAdapter.refreshUi(mArticleList);
            //设置控件结束动画
            if (isRefresh){
                isRefresh = false;
                mRefreshLayout.setRefreshing(false);
            }
        }
    }

    private void getBannerData(){
        Request request = new Request.Builder().url("https://www.wanandroid.com/banner/json").method("GET").build();
        mPresenter.getBannerData(request);
    }

    private void getArticleData(){
        //页码拼到url
        String url = "https://www.wanandroid.com/article/list/" + mPageId + "/json";
        Request request = new Request.Builder().url(url).method("GET").build();
        mPresenter.getArticleData(request);
    }
}

