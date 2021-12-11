package com.example.classifyhomescreen;

public class DateItem {
    String date;

    DateItem(String date){
        String month;
        String day;
        String year;
        String fullDate;
        fullDate = date;
        String str [] = fullDate.split("/");
        month = str[0];
        day = str[1];
        year = str[2];
        this.date = year+"-"+month+"-"+day;
    }

    public String returnDate(){
        return this.date;
    }
}
