package com.mredrock.wanandroid.bean;

/**
 * @author 行云流水
 * @date 2020/3/26
 * @description
 */
public class ProgramType {

    private int id;
    private String name;

    public ProgramType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
