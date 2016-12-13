package com.example.nikom.medicalnewsv2;

/**
 * Created by nikom on 13/12/2016.
 */

public class newsItem {
    private String newsHeading;
    private String newsDesc;
    private String imageID;
    private String time;
    private String date;
    private String url;
    private String descSmall;

    public newsItem(String newsHeading, String newsDesc, String date, String time, String url, String imageID) {
        this.newsHeading = newsHeading;
        this.newsDesc = newsDesc;
        this.imageID = imageID;
        this.time = time;
        this.date = date;
        this.url = url;
        this.descSmall = this.newsDesc.substring(0, 70) + " ...";
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public String getImageID() {
        return imageID;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getDescSmall() {
        return descSmall;
    }
}