package com.compassecg.test720.compassecg.CommunityForum.bean;

import java.util.List;

/**
 * Created by hp on 2017/1/6.
 */

public class Answer {
    private String id;
    private String uid;
    private String pid;
    private String content;
    private String voice;

    private String zan_num;

    private String pl_num;
    private String time;
    private String pic;
    private String nickname;
    private List<Img> img;

    private String is_zan;
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

    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getPid() {
        return pid;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
    public String getVoice() {
        return voice;
    }



    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
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

    public void setImg(List<Img> img) {
        this.img = img;
    }

    public List<Img> getImg() {
        return img;
    }

    public String getPl_num() {
        return pl_num;
    }

    public String getIs_zan() {
        return is_zan;
    }

    public void setIs_zan(String is_zan) {
        this.is_zan = is_zan;
    }

    public void setPl_num(String pl_num) {
        this.pl_num = pl_num;
    }

    public String getZan_num() {
        return zan_num;
    }

    public void setZan_num(String zan_num) {
        this.zan_num = zan_num;
    }
}
