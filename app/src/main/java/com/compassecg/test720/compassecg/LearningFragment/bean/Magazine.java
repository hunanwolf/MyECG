package com.compassecg.test720.compassecg.LearningFragment.bean;

/**
 * Created by hp on 2017/1/9.
 */

public class Magazine {
    private String id;
    private String uid;
    private String title;
    private String cover;
    private String url;

    private String is_down;

    public String getIs_down() {
        return is_down;
    }

    public void setIs_down(String is_down) {
        this.is_down = is_down;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUid() {
        return uid;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getCover() {
        return cover;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "Magazine{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", url='" + url + '\'' +
                ", is_down='" + is_down + '\'' +
                '}';
    }
}
