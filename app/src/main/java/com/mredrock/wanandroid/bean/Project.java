package com.mredrock.wanandroid.bean;

import android.graphics.Bitmap;

import com.mredrock.wanandroid.network.getBitmap.BitmapCallBack;
import com.mredrock.wanandroid.network.getBitmap.GetBitmap;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class Project {

    private String author;
    private String desc;
    private String envelopePic;
    private String link;
    private String niceDate;
    private Bitmap envelopePicBitmap = null;

    public Project(String author, String desc, String envelopePic, String link, String niceDate) {
        this.author = author;
        this.desc = desc;
        this.envelopePic = envelopePic;
        this.link = link;
        this.niceDate = niceDate;
        if (envelopePic.equals(null)) {
            GetBitmap.getInstance().execute(envelopePic, new BitmapCallBack() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    setEnvelopePicBitmap(bitmap);
                }

                @Override
                public void onFailed(Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


    public String getAuthor() {
        return author;
    }

    public String getDesc() {
        return desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public String getLink() {
        return link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    private void setEnvelopePicBitmap(Bitmap bitmap) {
        this.envelopePicBitmap = bitmap;
    }

    public Bitmap getEnvelopePicBitmap() {
        return envelopePicBitmap;
    }
}
