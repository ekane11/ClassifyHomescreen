package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SocialEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_events);
    }
    public void onAddButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
        startActivity(intent);
    }

    public void onBack(View v) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }
}