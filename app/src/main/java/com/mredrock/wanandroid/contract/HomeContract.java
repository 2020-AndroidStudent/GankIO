package com.mredrock.wanandroid.contract;

import com.mredrock.wanandroid.base.IModel;
import com.mredrock.wanandroid.base.IPresenter;
import com.mredrock.wanandroid.base.IView;
import com.mredrock.wanandroid.bean.ArticleBean;
import com.mredrock.wanandroid.bean.BannerBean;
import com.mredrock.wanandroid.network.httphelper.Request;

import java.util.List;

/**
 * @author 珝珞
 * @date 2020/4/1
 * @project name WanAndroid
 *
 */
public interface HomeContract {
    interface Model extends IModel {
        //得到轮播图数据
        void getBannerData(Request request);
        //得到文章数据
        void getArticleData(Request request);
    }

    interface View extends IView {
        //banner数据回调 这里用接口返回的BannerBean
        void onBannerData(List<BannerBean> data);
        //文章数据回调
        void onArticleData(List<ArticleBean> data);
    }

    interface Presenter extends IPresenter {
        //请求banner数据
        void getBannerData(Request request);
        //首页文章
        void getArticleData(Request request);
    }
}
