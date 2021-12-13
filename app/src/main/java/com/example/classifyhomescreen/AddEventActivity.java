package com.example.classifyhomescreen;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private int noteid = -1;
    private int note_count;
    private static int total_num_notes = 0;
    DatePickerDialog picker;
    TimePickerDialog timePicker;
    EditText dateText;
    EditText locationText;
    EditText titleText;
    EditText timeText;
    EditText friendText;
    Switch alertText;
    String type;
    Calendar c;
    private FirebaseAuth mAuth;
    private FirebaseAuth mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);
        note_count = intent.getIntExtra("note_num", -1);
        type = intent.getStringExtra("type");

        dateText = (EditText) findViewById(R.id.dateText);
        locationText = (EditText) findViewById(R.id.locationText);
        titleText = (EditText) findViewById(R.id.titleText);
        timeText = (EditText) findViewById(R.id.timeText);
        friendText = (EditText) findViewById(R.id.friendText);
        alertText = (Switch) findViewById(R.id.alert_toggle);


        dateText.setInputType(InputType.TYPE_NULL);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                c = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateText.setText((monthOfYear + 1)+ "/" + dayOfMonth + "/" + year);
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.MONTH, monthOfYear);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        timeText.setInputType(InputType.TYPE_NULL);
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePicker = new TimePickerDialog(AddEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                timeText.setText(sHour + ":" + sMinute);
                                c.set(Calendar.HOUR_OF_DAY, sHour);
                                c.set(Calendar.MINUTE, sMinute);
                                c.set(Calendar.SECOND, 0);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });
        if(noteid != -1) {
            switch (type) {
                case "extra":
                    Event extraEvent = ExtraEventsActivity.events.get(note_count);
                    dateText.setText(extraEvent.getDate());
                    locationText.setText(extraEvent.getLocation());
                    timeText.setText(extraEvent.getTime());
                    titleText.setText(extraEvent.getTitle());
                    break;
                case "social":
                    Event socialEvent = SocialEventsActivity.events.get(note_count);
                    dateText.setText(socialEvent.getDate());
                    locationText.setText(socialEvent.getLocation());
                    timeText.setText(socialEvent.getTime());
                    titleText.setText(socialEvent.getTitle());
                    break;
                case "school":
                    Event schoolEvent = SchoolEventsActivity.events.get(note_count);
                    dateText.setText(schoolEvent.getDate());
                    locationText.setText(schoolEvent.getLocation());
                    timeText.setText(schoolEvent.getTime());
                    titleText.setText(schoolEvent.getTitle());
                    break;
                case "work":
                    Event workEvent = WorkEventsActivity.events.get(note_count);
                    dateText.setText(workEvent.getDate());
                    locationText.setText(workEvent.getLocation());
                    timeText.setText(workEvent.getTime());
                    titleText.setText(workEvent.getTitle());
                    break;
            }
        }
    }

    public void onButtonClick(View v) {

        boolean datePresent = true;
        String date = dateText.getText().toString();
        String location = locationText.getText().toString();
        String title = titleText.getText().toString();
        String time = timeText.getText().toString();
        String email = friendText.getText().toString();
        boolean alert = alertText.isChecked();
        String friend = friendText.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("events", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        if(date.length() < 8) {
            datePresent = false;
        }

        System.out.println("n:"+note_count);
        if(noteid == -1 && datePresent) {
            //send email to friend functionality should go here (email variable contains the email of friend)
            //alert stuff goes here too


            dbHelper.saveEvent(Login.username, date, location, time, title, type, total_num_notes);
            total_num_notes++;
//            Bundle params = new Bundle();
//            String name = "Event";
//            String text = title;
//            params.putString("image_name", name);
//            params.putString("full_text", text);
//            mFirebaseAnalytics = FirebaseAuth.getInstance();
//            mFirebaseAnalytics.logEvent("added_event", params);
            if(friendText!=null) {
                dbHelper.saveEvent(friend, date, location, time, title, type, total_num_notes);
                total_num_notes++;
                friendText.setText(null);
            }
            System.out.println("t:"+total_num_notes);

            if(alert) {
                //Notifications();
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(this, MyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1,
                        intent, 0);
                //get time in millis
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),  pendingIntent);

            }
        } else {
            if(datePresent) {
                dbHelper.updateEvent(Login.username, date, location, time, title, type, note_count);
            }
        }

        if(datePresent) {
            Intent intent = null;
            switch (type) {
                case "extra":
                    intent = new Intent(getApplicationContext(), ExtraEventsActivity.class);
                    startActivity(intent);
                    break;
                case "school":
                    intent = new Intent(getApplicationContext(), SchoolEventsActivity.class);
                    startActivity(intent);
                    break;
                case "social":
                    intent = new Intent(getApplicationContext(), SocialEventsActivity.class);
                    startActivity(intent);
                    break;
                case "work":
                    intent = new Intent(getApplicationContext(), WorkEventsActivity.class);
                    startActivity(intent);
                    break;
            }
        } else {
            Snackbar mySnackbar = Snackbar.make(v, "please enter a date", 1000);
            mySnackbar.show();
        }

    }

    public void onBack(View v) {
        finish();
    }

}