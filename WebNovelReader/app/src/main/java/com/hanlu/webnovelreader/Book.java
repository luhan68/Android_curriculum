package com.hanlu.webnovelreader;

public class Book {

    public String getCoverPictureURL() {
        return coverPictureURL;
    }

    public String getTitle() {
        return title;
    }

    public String getUnread(){
        return unread;
    }

    private String coverPictureURL, title, unread;

    public Book(String coverPictureURL, String title, String unread) {
        this.coverPictureURL = coverPictureURL;
        this.title = title;
        this.unread = unread;
    }
}
