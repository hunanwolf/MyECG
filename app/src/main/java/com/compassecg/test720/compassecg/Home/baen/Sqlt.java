package com.compassecg.test720.compassecg.Home.baen;

import com.compassecg.test720.compassecg.CommunityForum.bean.Img;

import java.util.List;

/**
 * Created by hp on 2017/1/6.
 */

public class Sqlt {
    private String id;
    private String uid;
    private String title;
    private String time;
    private String asw_num;

    private String pic;
    private String nickname;
    private List<Img> img;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getAsw_num() {
        return asw_num;
    }

    public void setAsw_num(String asw_num) {
        this.asw_num = asw_num;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public List<Img> getImg() {
        return img;
    }

    public void setImg(List<Img> img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Sqlt{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", asw_num='" + asw_num + '\'' +
                ", pic='" + pic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", img=" + img +
                '}';
    }
}
