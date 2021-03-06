package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize & assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener((item -> {
            switch(item.getItemId()){
                case R.id.dashboard:
                    startActivity(new Intent(getApplicationContext()
                    ,Dashboard.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.home:
                    return true;
                case R.id.calendar:
                    startActivity(new Intent(getApplicationContext()
                            , ClassifyCalendar.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }));
    }

    /**
     *Funcationality for moving to different categories
     * & to profile or notifications
     * change "xxx.class" to match different classes
     **/
    public void academicClick(View view){
        Intent intent = new Intent(this, SchoolEventsActivity.class);
        startActivity(intent);
    }

    public void extracurricularClick(View view){
        Intent intent = new Intent(this, ExtraEventsActivity.class);
        startActivity(intent);

    }

    public void socialClick(View view){
        Intent intent = new Intent(this, SocialEventsActivity.class);
        startActivity(intent);
    }

    public void workClick(View view){
        Intent intent = new Intent(this, WorkEventsActivity.class);
        startActivity(intent);
    }
/**
    public void otherClick(View view){
        Intent intent = new Intent(this, other.class);
        startActivity(intent);
    }
 **/

    public void profileClick(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void logoutClick(View view){
        FirebaseAuth.getInstance().signOut();
        Login.username = null;
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}