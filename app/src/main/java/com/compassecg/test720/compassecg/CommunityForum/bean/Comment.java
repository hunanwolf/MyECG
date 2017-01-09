package com.compassecg.test720.compassecg.CommunityForum.bean;

/**
 * Created by hp on 2017/1/6.
 */

public class Comment {
    private String id;
    private String uid;
    private String content;
    private String time;
    private String pic;
    private String nickname;
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

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", pic='" + pic + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
