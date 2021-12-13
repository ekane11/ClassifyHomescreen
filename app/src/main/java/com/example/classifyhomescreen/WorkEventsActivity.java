package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WorkEventsActivity extends AppCompatActivity {

    public static ArrayList<Event> events = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_events);


        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("events", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        events = dbHelper.readNotes(Login.username);

        ArrayList<String> displayEvents = new ArrayList<>();
        ArrayList<Event> workEvents = new ArrayList<Event>();
        for(Event event: events) {
            if (event.getType().equals("work")&&event.getUsername().equals(Login.username)) {
                displayEvents.add(String.format("Title: %s\nDate: %s", event.getTitle(), event.getDate()));
                workEvents.add(event);
            }
        }



        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayEvents);
        ListView listView = (ListView) findViewById(R.id.workEventsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                int num = workEvents.get(position).getNum();
                System.out.println(num);
                intent.putExtra("noteid", position); //in the list of separate categories
                intent.putExtra("note_num", num); //position in entire event
                intent.putExtra("type", "work");
                //System.out.println(num);
                startActivity(intent);
            }
        });
    }

    public void onAddButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
        intent.putExtra("type", "work");
        startActivity(intent);
    }

    public void onBack(View v) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }

}