package com.example.classifyhomescreen;

import java.util.List;

public class Event {

    private String title;
    private String date;
    private String time;
    private String location;
    private boolean alert;
    private String type;
    private String friend;
    private int note_num = 0;

    public Event(String title, String date, String time, String location, boolean alert, String friend, String type, int count) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.alert = alert;
        this.title = title;
        this.type = type;
        this.note_num = count;
        this.friend = friend;
    }

    public String getDate() {
        return this.date;
    }

    public String getFriend() { return this.friend;}

    public String getTime() {
        return this.time;
    }

    public String getLocation() {
        return this.location;
    }

    public boolean getAlert() {
        return this.alert;
    }

    public String getTitle() { return this.title; }

    public String getType() {return this.type;}

    public int getNum() {return note_num; }

}