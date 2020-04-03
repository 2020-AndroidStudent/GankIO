package com.mredrock.wanandroid.presenter;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mredrock.wanandroid.base.BasePresenter;
import com.mredrock.wanandroid.bean.ArticleBean;
import com.mredrock.wanandroid.bean.BannerBean;
import com.mredrock.wanandroid.contract.HomeContract;
import com.mredrock.wanandroid.model.HomeModel;
import com.mredrock.wanandroid.network.httphelper.Request;
import com.mredrock.wanandroid.view.fragment.HomeFragment;

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
        switch (msg.what){
            case 0://getBannerData接口请求后返回的
                String data = (String) msg.obj;//取出接口数据
                if (!TextUtils.isEmpty(data)){
                    JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
                    if (jsonObject != null && jsonObject.has("errorCode")){
                        int errorCode = jsonObject.get("errorCode").getAsInt();
                        if (errorCode == 0 && jsonObject.has("data")){
                            String  dataJson = jsonObject.get("data").getAsJsonArray().toString();
                            //解析为list
                            List<BannerBean> list = new Gson().fromJson(dataJson,new TypeToken<List<BannerBean>>(){}.getType());
                            if (mView != null){
                                mView.onBannerData(list);
                            }
                        }
                    }
                }
                break;
            case 1://getArticle接口返回
                String articleData = (String) msg.obj;//取出接口数据
                if (!TextUtils.isEmpty(articleData)){
                    JsonObject jsonObject = new JsonParser().parse(articleData).getAsJsonObject();
                    if (jsonObject != null && jsonObject.has("errorCode")){
                        int errorCode = jsonObject.get("errorCode").getAsInt();
                        if (errorCode == 0 && jsonObject.has("data")){
                            JsonObject  dataObj = jsonObject.get("data").getAsJsonObject();
                            //这个接口的数据格式和banner接口略有差异，后者data是数组，这里data里面的datas才是数组
                            if (dataObj != null && dataObj.has("datas")){
                                String dataJson = dataObj.get("datas").getAsJsonArray().toString();
                                //解析为list
                                List<ArticleBean> list = new Gson().fromJson(dataJson,new TypeToken<List<ArticleBean>>(){}.getType());
                                if (mView != null){
                                    mView.onArticleData(list);
                                }
                            }
                        }
                    }
                }
                break;
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
