package com.example.classifyhomescreen;

import java.util.List;

public class Event {

    private String date;
    private String time;
    private String location;
    private boolean alert;

    public Event(String date, String time, String location, boolean alert, List<String> friends) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.alert = alert;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getLocation() {
        return this.location;
    }

    public boolean getAlert() {
        return this.alert;
    }
}