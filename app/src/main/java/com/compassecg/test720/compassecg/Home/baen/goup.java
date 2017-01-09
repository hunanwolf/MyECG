package com.compassecg.test720.compassecg.Home.baen;

/**
 * Created by hp on 2017/1/7.
 */

public class goup {
    private String id;
    private String uid;
    private String name;
    private String cover;
    private String num;
    private int is_gid;

    public int getIs_gid() {
        return is_gid;
    }

    public void setIs_gid(int is_gid) {
        this.is_gid = is_gid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "goup{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", num='" + num + '\'' +
                ", is_gid=" + is_gid +
                '}';
    }
}
