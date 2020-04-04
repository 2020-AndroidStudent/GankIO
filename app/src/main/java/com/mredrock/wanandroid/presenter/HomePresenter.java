package com.mredrock.wanandroid.presenter;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.mredrock.wanandroid.base.BasePresenter;
import com.mredrock.wanandroid.bean.ArticleBean;
import com.mredrock.wanandroid.bean.BannerBean;
import com.mredrock.wanandroid.contract.HomeContract;
import com.mredrock.wanandroid.model.HomeModel;
import com.mredrock.wanandroid.network.httphelper.Request;
import com.mredrock.wanandroid.view.fragment.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 珝珞
 * @date 2020/3/26
 * @project name WanAndroid
 */
public class HomePresenter extends BasePresenter<HomeContract.Model,HomeContract.View> implements HomeContract.Presenter {

    //这里要指向IView的实例
    public HomePresenter(HomeFragment view) {
        super(view);
    }

    @Override
    public HomeModel initModel(Handler handler) {
        return new HomeModel(handler);
    }

    @Override
    public void modelResponse(Message msg) {
        try {
            switch (msg.what){
            case 0://getBannerData接口请求后返回的
                String data = (String) msg.obj;//取出接口数据
                if (!TextUtils.isEmpty(data)) {
                    JSONObject jsonObject = null;
                        jsonObject = new JSONObject(data);
                    if (jsonObject.has("errorCode")) {
                        int errorCode = jsonObject.getInt("errorCode");
                        if (errorCode == 0 && jsonObject.has("data")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            List<BannerBean> list = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                BannerBean bannerBean = new BannerBean();
                                if (obj.has("title")){
                                    bannerBean.setTitle(obj.getString("title"));
                                }
                                if (obj.has("imagePath")){
                                    bannerBean.setImagePath(obj.getString("imagePath"));
                                }
                                list.add(bannerBean);
                            }
                            if (mView != null) {
                                mView.onBannerData(list);
                            }
                        }
                    }
                }
                break;
            case 1://getArticle接口返回
                String articleData = (String) msg.obj;//取出接口数据
                if (!TextUtils.isEmpty(articleData)) {
                    JSONObject jsonObject = new JSONObject(articleData);
                    if (jsonObject.has("errorCode")) {
                        int errorCode = jsonObject.getInt("errorCode");
                        if (errorCode == 0 && jsonObject.has("data")) {
                            JSONObject dataObj = jsonObject.getJSONObject("data");
                            //这个接口的数据格式和banner接口略有差异，后者data是数组，这里data里面的datas才是数组
                            if (dataObj.has("datas")) {
                                JSONArray jsonArray = dataObj.getJSONArray("datas");
                                List<ArticleBean> list = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    ArticleBean bean = new ArticleBean();
                                    if (obj.has("title")){
                                        bean.setTitle(obj.getString("title"));
                                    }
                                    if (obj.has("author")){
                                        bean.setAuthor(obj.getString("author"));
                                    }
                                    if (obj.has("niceDate")){
                                        bean.setNiceDate(obj.getString("niceDate"));
                                    }
                                    if (obj.has("superChapterName")){
                                        bean.setSuperChapterName(obj.getString("superChapterName"));
                                    }
                                    if (obj.has("link")){
                                        bean.setLink(obj.getString("link"));
                                    }
                                    list.add(bean);
                                }
                                if (mView != null) {
                                    mView.onArticleData(list);
                                }
                            }
                        }
                    }
                }
                break;
        }
        } catch (JSONException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void getBannerData(Request request) {
        mModel.getBannerData(request);
    }

    @Override
    public void getArticleData(Request request) {
        mModel.getArticleData(request);
    }
}
