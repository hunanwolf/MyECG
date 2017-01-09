package com.compassecg.test720.compassecg.CommunityForum.bean;

/**
 * Created by hp on 2017/1/5.
 */

public class Img {
    private String url;
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Img{" +
                "url='" + url + '\'' +
                '}';
    }
}
