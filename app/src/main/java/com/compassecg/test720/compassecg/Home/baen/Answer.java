package com.compassecg.test720.compassecg.Home.baen;

import com.compassecg.test720.compassecg.CommunityForum.bean.Img;

import java.util.List;

/**
 * Created by hp on 2017/1/7.
 */

public class Answer {
    private String id;
    private String content;
    private String time;
    private String pic;
    private String nickname;
    private String job;
    private String hospital;
    private String desk;
    private String pl_num;
    private List<Img> img;

    public List<Img> getImg() {
        return img;
    }

    public void setImg(List<Img> img) {
        this.img = img;
    }

    public String getPl_num() {
        return pl_num;
    }

    public void setPl_num(String pl_num) {
        this.pl_num = pl_num;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", pic='" + pic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", job='" + job + '\'' +
                ", hospital='" + hospital + '\'' +
                ", desk='" + desk + '\'' +
                ", pl_num='" + pl_num + '\'' +
                ", img=" + img +
                '}';
    }
}
