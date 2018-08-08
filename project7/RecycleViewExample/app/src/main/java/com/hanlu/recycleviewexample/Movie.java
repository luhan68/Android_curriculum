package com.hanlu.recycleviewexample;

public class Movie {
    private String title, genre, year;

    public Movie(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYest(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
