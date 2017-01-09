package com.compassecg.test720.compassecg.Home.baen;

/**
 * Created by hp on 2017/1/7.
 */

public class mygoup {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "mygoup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
