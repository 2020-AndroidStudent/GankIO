package com.mredrock.wanandroid.bean;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class ProjectType {

    private String id;
    private String name;

    public ProjectType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
