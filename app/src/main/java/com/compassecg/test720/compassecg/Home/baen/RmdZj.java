package com.compassecg.test720.compassecg.Home.baen;

/**
 * Created by hp on 2017/1/6.
 */

public class RmdZj {
    private String id;
    private String pic;
    private String nickname;
    private String job;
    private String hospital;
    private String desk;
    private int is_gz;

    public int getIs_gz() {
        return is_gz;
    }

    public void setIs_gz(int is_gz) {
        this.is_gz = is_gz;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospital() {
        return hospital;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getDesk() {
        return desk;
    }

    @Override
    public String toString() {
        return "RmdZj{" +
                "id='" + id + '\'' +
                ", pic='" + pic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", job='" + job + '\'' +
                ", hospital='" + hospital + '\'' +
                ", desk='" + desk + '\'' +
                ", is_gz=" + is_gz +
                '}';
    }
}
