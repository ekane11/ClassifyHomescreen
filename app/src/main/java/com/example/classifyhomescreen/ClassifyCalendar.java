package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClassifyCalendar extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2){
                String date = (i1+1) + "/" + i2 + "/" + i;
                //Log.d("date mm/dd/yyyy", date);
                Intent intent = new Intent(ClassifyCalendar.this, CalendarDisplay.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });


        //Initialize & assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.calendar);

        bottomNavigationView.setOnItemSelectedListener((item -> {
            switch(item.getItemId()){
                case R.id.dashboard:
                    startActivity(new Intent(getApplicationContext()
                            ,Dashboard.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext()
                            , Home.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.calendar:
                    return true;
            }
            return false;
        }));
    }
}