package com.mredrock.wanandroid.bean;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class Program {

    private String author;
    private String desc;
    private String envelopePic;
    private String link;
    private String niceShareDate;

    public Program(String author, String desc, String envelopePic, String link, String niceShareDate) {
        this.author = author;
        this.desc = desc;
        this.envelopePic = envelopePic;
        this.link = link;
        this.niceShareDate = niceShareDate;
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

    public String getNiceShareDate() {
        return niceShareDate;
    }
}
