package com.compassecg.test720.compassecg.LearningFragment.bean;

/**
 * Created by hp on 2017/1/9.
 */

public class doctorList {

    private String id;
    private String nickname;
    private String pic;
    private String job;
    private String hospital;
    private String desk;

    private int is_gz;

    private int is_yq;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getPic() {
        return pic;
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

    public int getIs_yq() {
        return is_yq;
    }

    public void setIs_yq(int is_yq) {
        this.is_yq = is_yq;
    }

    public int getIs_gz() {
        return is_gz;
    }

    public void setIs_gz(int is_gz) {
        this.is_gz = is_gz;
    }

    @Override
    public String toString() {
        return "doctorList{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pic='" + pic + '\'' +
                ", job='" + job + '\'' +
                ", hospital='" + hospital + '\'' +
                ", desk='" + desk + '\'' +
                ", is_gz=" + is_gz +
                ", is_yq=" + is_yq +
                '}';
    }
}
