package com.example.awningmanufacturer;

public class VideoItem {
    private String title;
    private String url;
    private String image;

    public VideoItem(String title, String url, String image) {
        this.title = title;
        this.url = url;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
