package com.hanlu.doordash_v2;

import java.util.ArrayList;

public class Food {

    private String title;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public ArrayList<String> getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(ArrayList<String> picture_url) {
        this.picture_url = picture_url;
    }

    public Food(String title, String type, String reviews, String time, String delivery_fee, ArrayList<String> picture_url) {

        this.title = title;
        this.type = type;
        this.reviews = reviews;
        this.time = time;
        this.delivery_fee = delivery_fee;
        this.picture_url = picture_url;
    }

    private String reviews;
    private String time;
    private String delivery_fee;
    private ArrayList<String> picture_url;
}