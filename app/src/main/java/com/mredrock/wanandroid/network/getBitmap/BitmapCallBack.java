package com.mredrock.wanandroid.network.getBitmap;

import android.graphics.Bitmap;

/**
 * @author 行云流水
 * @date 2020/4/1
 * @description
 */
public interface BitmapCallBack {
    void onResponse(Bitmap bitmap);

    void onFailed(Exception e);
}
