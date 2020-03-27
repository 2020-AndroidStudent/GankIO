package com.mredrock.wanandroid.bean;

/**
 * @author 珝珞
 * @date 2020/3/26
 * @project name WanAndroid
 */
public class LooperItem {
    private String title;
    private Integer picResId;

    public LooperItem(String title, Integer picResId) {
        this.title = title;
        this.picResId = picResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPicResId() {
        return picResId;
    }

    public void setPicResId(Integer picResId) {
        this.picResId = picResId;
    }
}
