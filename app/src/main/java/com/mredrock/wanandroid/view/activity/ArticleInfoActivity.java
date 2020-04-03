package com.mredrock.wanandroid.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import com.mredrock.wanandroid.R;
import com.mredrock.wanandroid.base.BaseActivity;
import com.mredrock.wanandroid.base.IPresenter;


/**
 * @author 珝珞
 * @date 2020/4/1
 * @project name WanAndroid
 *
 */
public class ArticleInfoActivity extends BaseActivity {
    private WebView mWebView;
    @Override
    public IPresenter initPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articleinfo);
        initView();
        initData();
    }

   private void initView() {
        mWebView = findViewById(R.id.webview);
    }


    public void initData() {
        if (getIntent() != null && getIntent().hasExtra("url")){
            String url = getIntent().getStringExtra("url");
            if (!TextUtils.isEmpty(url) && mWebView != null){
                mWebView.loadUrl(url);
            }
        }
    }
}
