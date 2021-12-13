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
        events = dbHelper.readNotes(Login.username);

        ArrayList<String> displayEvents = new ArrayList<>();
        ArrayList<DateItem> dateList = new ArrayList<>();
        ArrayList<String> orgDateOrder = new ArrayList<>();
        ArrayList<Event> clickEvents = new ArrayList<>();
        ArrayList<Event> clickEvents2 = new ArrayList<>();
        for(Event event: events) {
            if(event.getUsername().equals(Login.username)) {
                displayEvents.add(String.format("Title: %s\nDate: %s", event.getTitle(), event.getDate()));
                dateList.add(new DateItem(event.getDate()));
                orgDateOrder.add(event.getDate());
                clickEvents.add(event);
            }
        }

        Collections.sort(dateList, new sortItems());
        ArrayList<String> finalDisplay = new ArrayList<>();

        System.out.println("datelist: " + dateList.size());
        System.out.println("displayevents: " + displayEvents.size());
        for(int i = 0; i < dateList.size(); i++) {
            for (int j = 0; j < displayEvents.size();j++){
                //create new array to store dates but don't change order?
                //then compare
                String fullDate = orgDateOrder.get(j);
                String str [] = fullDate.split("/");
                String month = str[0];
                String day = str[1];
                String year = str[2];
                String formatDate = year+"-"+month+"-"+day;
                if(dateList.get(i).returnDate().equals(formatDate)){
                    finalDisplay.add(displayEvents.get(j));
                    clickEvents2.add(clickEvents.get(j));
                    /**
                    displayEvents.remove(j);
                    j--;
                    break;
                     **/
                }
            }
        }

        System.out.println(finalDisplay.size());
        ArrayList<String> finalArray = new ArrayList<>();
        for(String s : finalDisplay){
            if(!finalArray.contains(s)){
                finalArray.add(s);
            }
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, finalArray);
        ListView listView = findViewById(R.id.DashListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                int num = clickEvents2.get(position).getNum();
                String type = clickEvents2.get(position).getType();
                intent.putExtra("noteid", position); //in the list of separate categories
                intent.putExtra("note_num", num); //position in entire event
                intent.putExtra("type", type);
                //System.out.println(num);
                startActivity(intent);
            }
        });
    }
}
