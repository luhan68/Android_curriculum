package com.hanlu.youtuberecycleview;

import android.provider.ContactsContract;
import android.widget.ImageView;

public class Video {

    private String author_name, title, author_url, video_url;

    public Video(String author_name, String title, String author_photo, String video_pic)
    {
        this.author_name = author_name;
        this.title = title;
        author_url = author_photo;
        video_url = video_pic;
    }

    public void setAuthorName(String author_name)
    {
        this.author_name = author_name;
    }

    public void setVideoTitle(String title)
    {
        this.title = title;
    }

    public void setAuthorPhoto(String author_photo)
    {
        author_url = author_photo;
    }

    public void setVideoPic(String video_pic)
    {
        video_url = video_pic;
    }

    public String getAuthorName(){
        return author_name;
    }

    public String getVideoTitle(){
        return title;
    }

    public String getAuthorPhoto() {
        return author_url;
    }

    public String getVideoPic() {
        return video_url;
    }
}
