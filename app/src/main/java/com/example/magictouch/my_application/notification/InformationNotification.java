package com.example.magictouch.my_application.notification;

/**
 * Created by tarfa on 6/6/18.
 */

public  class InformationNotification {
    private String title;
    private String date;
    private String type;

    public InformationNotification(String title, String date, String type) {
        this.title = title;
        this.date = date;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
