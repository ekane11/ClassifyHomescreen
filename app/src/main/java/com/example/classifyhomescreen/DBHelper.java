package com.example.classifyhomescreen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBHelper {

    SQLiteDatabase sqLiteDatabase;

    public DBHelper(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events" + "(id INTEGER PRIMARY KEY, username TEXT, title TEXT, date TEXT, time TEXT, type TEXT, location TEXT, count TEXT, src TEXT)");
    }

    public ArrayList<Event> readNotes(String username1) {

        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * from events" ), null);

        int titleIndex = c.getColumnIndex("title");
        int dateIndex = c.getColumnIndex("date");
        int locationIndex = c.getColumnIndex("location");
        int timeIndex = c.getColumnIndex("time");
        int typeIndex = c.getColumnIndex("type");
        int countIndex = c.getColumnIndex("count");
        int usernameIndex = c.getColumnIndex("username");


        c.moveToFirst();
        ArrayList<Event> eventsList = new ArrayList<>();

        while(!c.isAfterLast()) {

            String date = c.getString(dateIndex);
            String location = c.getString(locationIndex);
            String title = c.getString(titleIndex);
            String time = c.getString(timeIndex);
            String type = c.getString(typeIndex);
            int count = c.getInt(countIndex);
            String username = c.getString(usernameIndex);

            Event event = new Event(username, title, date, time, location, false, null, type, count);
            eventsList.add(event);
            c.moveToNext();
        }
        c.close();
        sqLiteDatabase.close();
        return eventsList;
    }

    public void saveEvent(String username, String date, String location, String time, String title, String type, int count) {
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO events (username, title, date, time, location, type, count) VALUES ('%s', '%s', '%s','%s','%s','%s', '%s')", username, title, date, time, location, type, count));
    }

    public void updateEvent(String username, String date, String location, String time, String title, String type, int count) {
        createTable();
        sqLiteDatabase.execSQL(String.format("UPDATE events set location = '%s', date = '%s', time = '%s', type = '%s', title = '%s' where username = '%s' and count = '%s'", location, date, time, type, title, username,count));
    }


}