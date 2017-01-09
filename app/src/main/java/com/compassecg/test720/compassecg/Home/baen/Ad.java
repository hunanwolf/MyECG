package com.compassecg.test720.compassecg.Home.baen;

/**
 * Created by hp on 2017/1/6.
 */

public class Ad {

    private String id;
    private String cover;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getCover() {
        return cover;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id='" + id + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
