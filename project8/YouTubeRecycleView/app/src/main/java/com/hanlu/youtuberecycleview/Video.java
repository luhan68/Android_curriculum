package com.hanlu.youtuberecycleview;

import android.provider.ContactsContract;
import android.widget.ImageView;

public class Video {

    private String video_img_url;
    private String video_url;
    private String author_title_url;
    private String video_title;
    private String author_title;

    public String getVideo_img_url() {
        return video_img_url;
    }

    public void setVideo_img_url(String video_img_url) {
        this.video_img_url = video_img_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getAuthor_title_url() {
        return author_title_url;
    }

    public void setAuthor_title_url(String author_title_url) {
        this.author_title_url = author_title_url;
    }

    public String getAuthor_title() {
        return author_title;
    }

    public void setAuthor_title(String author_title) {
        this.author_title = author_title;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }


    public Video(String video_img_url, String video_url, String author_title_url, String author_title, String video_title) {
        this.video_img_url = video_img_url;
        this.video_url = video_url;
        this.author_title_url = author_title_url;
        this.author_title = author_title;
        this.video_title = video_title;
    }


}