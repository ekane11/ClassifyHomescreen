package com.example.classifyhomescreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventActivity extends AppCompatActivity {

    private int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        EditText dateText = (EditText) findViewById(R.id.dateText);
        EditText locationText = (EditText) findViewById(R.id.locationText);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);
        String str = intent.getStringExtra("type");

        if(noteid != -1) {
            if(str.equals("work")) {
                Event event = WorkEventsActivity.events.get(noteid);
                String eventLocation = event.getLocation();
                String eventDate = event.getDate();
                dateText.setText(eventDate);
                locationText.setText(eventLocation);
            }

        }
    }

    public void onButtonClick(View v) {
        EditText locationText = (EditText) findViewById(R.id.locationText);
        EditText dateText = (EditText) findViewById(R.id.dateText);
        String date = dateText.getText().toString();
        String location = locationText.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("events", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        //SharedPreferences sharedPreferences = getSharedPreferences("com.example.milestone_1", Context.MODE_PRIVATE);
        //String username = sharedPreferences.getString("username", "");
        //String title;
        //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //String date = dateFormat.format(new Date());
        if(noteid == -1) {
            dbHelper.saveEvent("username", date, location);
        } else {
            dbHelper.updateEvent("username", date, location);
        }


        Intent intent = new Intent(this, WorkEventsActivity.class);
        startActivity(intent);
    }

    public void onBack(View v) {
        finish();
    }
}