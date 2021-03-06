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
import android.widget.TextView;

import java.util.ArrayList;

public class CalendarDisplay extends AppCompatActivity {

    private TextView theDate;
    public static ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);
        theDate = (TextView) findViewById(R.id.DateDisplay);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("events",
                Context.MODE_PRIVATE, null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        //Have to change username
        events = dbHelper.readNotes(Login.username);

        ArrayList<String> displayEvents = new ArrayList<>();
        ArrayList<Event> clickEvents = new ArrayList<>();
        for(Event event: events){
            //Find out what other data to display
            if(event.getDate().equals(date)&&event.getUsername().equals(Login.username)) {
                displayEvents.add(String.format("Title:%s", event.getTitle()));
                clickEvents.add(event);
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                displayEvents);
        ListView listView = (ListView) findViewById(R.id.CalendarDisplayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                int num = clickEvents.get(position).getNum();
                String type = clickEvents.get(position).getType();
                intent.putExtra("noteid", position); //in the list of separate categories
                intent.putExtra("note_num", num); //position in entire event
                intent.putExtra("type", type);
                //System.out.println(num);
                startActivity(intent);
            }
        });

    }

    public void back(View view){
        finish();
    }
}