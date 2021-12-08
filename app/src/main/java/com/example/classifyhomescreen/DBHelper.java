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
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events" + "(id INTEGER PRIMARY KEY, username TEXT, date TEXT, location TEXT)");
    }

    public ArrayList<Event> readNotes(String username) {

        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * from events where username like '%s'", username), null);

        int dateIndex = c.getColumnIndex("date");
        int locationIndex = c.getColumnIndex("location");

        c.moveToFirst();
        ArrayList<Event> eventsList = new ArrayList<>();

        while(!c.isAfterLast()) {

            String date = c.getString(dateIndex);
            String location = c.getString(locationIndex);

            Event event = new Event(username, date, location, false, null);
            eventsList.add(event);
            c.moveToNext();
        }
        c.close();
        sqLiteDatabase.close();
        return eventsList;
    }

    public void saveEvent(String username, String date, String location) {
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO events (username, date, location) VALUES ('%s', '%s', '%s')", username, date, location));
    }

    public void updateEvent(String username, String date, String location) {
        createTable();
        sqLiteDatabase.execSQL(String.format("UPDATE events set location = '%s', date = '%s' where username = '%s'", location, date, username));
    }

}