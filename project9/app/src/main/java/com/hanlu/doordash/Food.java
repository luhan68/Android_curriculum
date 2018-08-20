package com.hanlu.doordash;

public class Food {

    private String title, intro, picture_url, name, info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Food(String title, String intro, String picture_url, String name, String info) {
        this.title = title;
        this.intro = intro;
        this.picture_url = picture_url;
        this.name = name;
        this.info = info;
    }
}
