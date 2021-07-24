package com.bmcc.model;

public class Announcement {
    private String headline;
    private String content;

    public Announcement(String headline, String content) {
        this.headline = headline;
        this.content = content;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
