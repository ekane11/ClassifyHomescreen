package com.example.classifyhomescreen;

public class DateItem {
    String date;

    DateItem(String date){
            String month;
            String day;
            String year;
            String fullDate;
            fullDate = date;
            String str[] = fullDate.split("/");
            month = str[0];
            day = str[1];
            year = str[2];
            this.date = year + "-" + month + "-" + day;

            //this.date = "0000-00-00";

    }

    public String returnDate(){
        return this.date;
    }
}
