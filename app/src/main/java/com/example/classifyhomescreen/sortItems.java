package com.example.classifyhomescreen;

import java.util.Comparator;

public class sortItems implements Comparator<DateItem> {

    @Override
    public int compare(DateItem dateItem, DateItem t1) {
        return dateItem.date.compareTo(t1.date);
    }
}
