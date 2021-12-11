package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dashboard extends AppCompatActivity {

    public static ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Initialize & assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnItemSelectedListener((item -> {
            switch(item.getItemId()){
                case R.id.dashboard:
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext()
                            , Home.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.calendar:
                    startActivity(new Intent(getApplicationContext()
                            ,ClassifyCalendar.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }));
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("events", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        events = dbHelper.readNotes("username");

        ArrayList<String> displayEvents = new ArrayList<>();
        for(Event event: events) {

            displayEvents.add(String.format("Title:%s\nDate:%s", event.getTitle(), event.getDate()));
        }



        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayEvents);
        ListView listView = findViewById(R.id.DashListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                intent.putExtra("noteid", position);
                intent.putExtra("type", "work");
                startActivity(intent);
            }
        });
    }
}