package com.compassecg.test720.compassecg.Home.baen;

/**
 * Created by anim on 2016/8/25.
 */

public class HomeW {
    private String id;
    private String route;
    private String imgtext;
    private String url;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setRoute(String route) {
        this.route = route;
    }
    public String getRoute() {
        return route;
    }

    public void setImgtext(String imgtext) {
        this.imgtext = imgtext;
    }
    public String getImgtext() {
        return imgtext;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "HomeW{" +
                "id='" + id + '\'' +
                ", route='" + route + '\'' +
                ", imgtext='" + imgtext + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
